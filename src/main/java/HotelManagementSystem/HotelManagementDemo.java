package HotelManagementSystem;
import HotelManagementSystem.Enums.*;
import HotelManagementSystem.Decorator.*;
import HotelManagementSystem.Strategy.*;
import java.time.LocalDate;

public class HotelManagementDemo {

        public static void main(String[] args) {
            HotelManagementSystem system = new HotelManagementSystem();

            // Register a guest
            system.registerGuest("Rohit Sharma", "rohit@email.com", "1234567890");

            // Add rooms
            system.addRoom("101", RoomType.SINGLE, RoomStyle.STANDARD);
            system.addRoom("102", RoomType.SUITE, RoomStyle.OCEAN_VIEW);

            // Book a room
            system.bookRoom("Rohit Sharma", "102", LocalDate.now(), LocalDate.now().plusDays(2));

            // Add features
            system.addRoomFeature("Rohit Sharma", new AirportPickup(null));
            system.addRoomFeature("Rohit Sharma", new Breakfast(null));

            // Check in
            system.checkIn("Rohit Sharma");

            // Pay with credit card
            system.pay("Rohit Sharma", new CreditCardPayment("1234567812345678"));

            // Check out
            system.checkOut("Rohit Sharma");
        }
    }


