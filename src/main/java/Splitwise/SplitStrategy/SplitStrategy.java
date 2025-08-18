package Splitwise.SplitStrategy;

import Splitwise.Split;
import Splitwise.User;

import java.util.List;

public interface SplitStrategy {

    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);

    default boolean requiresSplitValues(){
        return false;
    }
}
