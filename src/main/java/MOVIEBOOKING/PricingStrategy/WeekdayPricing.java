package MOVIEBOOKING.PricingStrategy;

import MOVIEBOOKING.Seat;
import MOVIEBOOKING.Show;

import java.util.List;

public class WeekdayPricing implements PricingStrategyInterface {

    @Override
    public double calculatePrice(List<Seat> seats, Show show) {
        double total = 0.0;
        for (Seat seat : seats) {
            total += seat.getSeatType().getBasePrice();
        }
        return total; // No surcharge on weekdays
    }
}