package Splitwise;

import java.util.*;
import java.util.stream.Collectors;

public class SplitwiseService {

    private static SplitwiseService instance;

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private final Map<String, Expense> expenses = new HashMap<>();

    private SplitwiseService() {}

    public static synchronized SplitwiseService getInstance() {
        if (instance == null) {
            instance = new SplitwiseService();
        }
        return instance;
    }

    // --- User management ---
    public synchronized User addUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    // --- Group management ---
    public synchronized Group addGroup(String name, List<User> members) {
        Group group = new Group(name, members);
        groups.put(group.getId(), group);
        return group;
    }

    public Group getGroup(String id) {
        return groups.get(id);
    }

    // --- Expense management ---
    public synchronized void createExpense(Expense.ExpenseBuilder builder) {
        Expense expense = builder.build();

        expenses.put(expense.getId(), expense);

        User paidBy = expense.getPaidBy();
        for (Split split : expense.getSplits()) {
            User participant = split.getUser();
            double amount = split.getAmount();

            if (!paidBy.equals(participant)) {
                // Update balances via balance sheets
                paidBy.addBalance(participant, amount);
                participant.subtractBalance(paidBy, amount);
            }
        }
        System.out.println("Expense '" + expense.getDescription() + "' of amount $" + expense.getAmount() + " created. (paid by " + paidBy.getName() + ")");
    }

    // --- Settlement between users ---
    public synchronized void settleUp(String payerId, String payeeId, double amount) {
        User payer = users.get(payerId);
        User payee = users.get(payeeId);

        if (payer == null || payee == null) {
            throw new IllegalArgumentException("Invalid payer or payee");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        System.out.println(payer.getName() + " is settling up $" + amount + " with " + payee.getName());

        // Settlement: reverse balances accordingly
        payee.subtractBalance(payer, amount);
        payer.addBalance(payee, amount);
    }

    // --- Show user's balance sheet ---
    public void showBalanceSheet(String userId) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found for id: " + userId);
            return;
        }
        user.showBalances();  // Call the method on user instance
    }

    // --- Debt simplification for a group ---  EXPLANATION IN NOTES
    private List<Map.Entry<User, Double>> getSortedCreditors(Map<User, Double> netBalances) {
        List<Map.Entry<User, Double>> creditors = new ArrayList<>();
        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() > 0) creditors.add(entry);
        }
        creditors.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // descending
        return creditors;
    }

    private List<Map.Entry<User, Double>> getSortedDebtors(Map<User, Double> netBalances) {
        List<Map.Entry<User, Double>> debtors = new ArrayList<>();
        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() < 0) debtors.add(entry);
        }
        debtors.sort(Comparator.comparingDouble(Map.Entry::getValue)); // ascending (more negative first)
        return debtors;
    }

    public List<Transaction> simplifyGroupDebts(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) throw new IllegalArgumentException("Group not found");

        // Calculate net balances (same as before)
        Map<User, Double> netBalances = new HashMap<>();
        for (User member : group.getMembers()) {
            double netBalance = 0;
            for (Map.Entry<User, Double> entry : member.getBalances().entrySet()) {
                if (group.getMembers().contains(entry.getKey())) {
                    netBalance += entry.getValue();
                }
            }
            netBalances.put(member, netBalance);
        }

        List<Map.Entry<User, Double>> creditors = getSortedCreditors(netBalances);
        List<Map.Entry<User, Double>> debtors = getSortedDebtors(netBalances);

        List<Transaction> transactions = new ArrayList<>();

        int i = 0, j = 0;
        while (i < creditors.size() && j < debtors.size()) {
            Map.Entry<User, Double> creditor = creditors.get(i);
            Map.Entry<User, Double> debtor = debtors.get(j);

            double settleAmount = Math.min(creditor.getValue(), -debtor.getValue());

            transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), settleAmount));

            creditor.setValue(creditor.getValue() - settleAmount);
            debtor.setValue(debtor.getValue() + settleAmount);

            if (Math.abs(creditor.getValue()) < 0.01) i++;
            if (Math.abs(debtor.getValue()) < 0.01) j++;
        }

        return transactions;
    }

}
