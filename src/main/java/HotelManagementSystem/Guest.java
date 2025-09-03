package HotelManagementSystem;

import java.util.UUID;

public class Guest {
    private final String guestId;
    private String name;
    private String email;
    private String phoneNumber;

    // To track guest's reservations (optional, helpful for history)
//    private final List<Reservation> reservationHistory = new ArrayList<>();

    public Guest(String name, String email, String phoneNumber) {
        this.guestId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters for updatable fields
    public void updateProfile(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

//    // Reservation history tracking
//    public void addReservation(Reservation reservation) {
//        reservationHistory.add(reservation);
//    }
//
//    public List<Reservation> getReservationHistory() {
//        return reservationHistory;
//    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId='" + guestId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
