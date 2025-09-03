package MOVIEBOOKING.PricingStrategy;

import MOVIEBOOKING.Seat;
import MOVIEBOOKING.Show;

import java.util.List;

public interface PricingStrategyInterface {
        double calculatePrice(List<Seat> seats, Show show);
    }

