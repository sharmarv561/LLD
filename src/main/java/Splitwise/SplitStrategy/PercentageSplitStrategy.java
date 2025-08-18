package Splitwise.SplitStrategy;

import Splitwise.Split;
import Splitwise.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues) {

        if (splitValues == null || splitValues.size() != participants.size()) {
            throw new IllegalArgumentException("Split values must be provided and match participants count.");
        }

       double totalPercent = 0;
       for(Double val : splitValues){
           totalPercent += val;
       }

       if(Math.abs(totalPercent - 100.00) > 0.01){
           throw  new IllegalArgumentException("Percentage split values do not sum upto 100");
       }

        List<Split> splits = new ArrayList<>();

       for(int i =0; i < participants.size(); i++){
           double share = totalAmount * splitValues.get(i)/100.00;
           splits.add(new Split(participants.get(i), share));
       }

       return splits;

    }

    @Override
    public boolean requiresSplitValues() {
        return true;
    }
}
