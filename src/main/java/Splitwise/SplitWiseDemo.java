package Splitwise;

import Splitwise.SplitStrategy.EqualSplitStrategy;

import java.util.Arrays;
import java.util.List;

public class SplitWiseDemo {
    public static void main(String[] args){

        SplitwiseService service = SplitwiseService.getInstance();

        // Create Users
        User alice = service.addUser("Alice", "alice@example.com");
        User bob = service.addUser("Bob", "bob@example.com");
        User charlie = service.addUser("Charlie", "charlie@example.com");

        // Create Group
        List<User> members = Arrays.asList(alice, bob, charlie);
        Group friendsGroup = service.addGroup("Friends Group", members);

//        // Add Expenses
        service.createExpense(new Expense.ExpenseBuilder()
                .setId("E1")
                .setDescription("Dinner")
                .setAmount(120)
                .setPaidBy(alice)
                .setParticipants(members)
                .setSplitStrategy(new EqualSplitStrategy())
        );

        // Exact split expense: Bob paid $150 for groceries, split exactly
        service.createExpense(new Expense.ExpenseBuilder()
                .setId("E2")
                .setDescription("Groceries")
                .setAmount(150)
                .setPaidBy(bob)
                .setParticipants(members)
                .setSplitStrategy(new Splitwise.SplitStrategy.ExactSplitStrategy())
                .setSplitValues(Arrays.asList(70.0, 50.0, 30.0))
        );

        // Show Balances for each user
        System.out.println("Before settlement:");
        service.showBalanceSheet(alice.getId());
        service.showBalanceSheet(bob.getId());
        service.showBalanceSheet(charlie.getId());

        // Settle Up: Charlie pays Alice $40
        service.settleUp(charlie.getId(), alice.getId(), 40);
        System.out.println("after settlement:");

        // Show balances after settlement
        service.showBalanceSheet(alice.getId());
        service.showBalanceSheet(charlie.getId());

        // Simplify debts for group and print transactions
        List<Transaction> transactions = service.simplifyGroupDebts(friendsGroup.getId());
        System.out.println("\nSimplified transactions to settle debts:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
