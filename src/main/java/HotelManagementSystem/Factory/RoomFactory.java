package HotelManagementSystem.Factory;

import HotelManagementSystem.Enums.RoomStyle;
import HotelManagementSystem.Enums.RoomType;
import HotelManagementSystem.Room;

public class RoomFactory {
    public static Room createRoom(String roomNumber, RoomType type, RoomStyle style) {
        double price = calculatePrice(type, style);
        return new Room(roomNumber, type, style, price);
    }

    private static double calculatePrice(RoomType type, RoomStyle style) {
        double basePrice;

        switch (type) {
            case SINGLE: basePrice = 100; break;
            case DOUBLE: basePrice = 150; break;
            case SUITE:  basePrice = 250; break;
            default:     basePrice = 100;
        }

        switch (style) {
            case DELUXE:      basePrice += 50; break;
            case OCEAN_VIEW:  basePrice += 100; break;
            default:          break; // STANDARD: no change
        }

        return basePrice;
    }
}
