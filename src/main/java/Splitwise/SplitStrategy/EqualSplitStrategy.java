package Splitwise.SplitStrategy;

import Splitwise.Split;
import Splitwise.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues) {
        List<Split> splits = new ArrayList<>();
        double equalShare = totalAmount / participants.size();
        for(User user : participants){
            if (user.equals(paidBy)) {
                splits.add(new Split(user, 0.0)); // Payer owes nothing
            } else {
                splits.add(new Split(user, equalShare));
            }        }

        return splits;
    }
}
