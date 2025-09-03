package HotelManagementSystem.Strategy;

public class OnlinePayment implements PaymentStrategy{

    private final String email;

    public OnlinePayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Online Wallet linked to " + email);
    }
}
