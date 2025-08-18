package FactoryPattern;

public class SmsNotificationCreator implements NotificationCreator{
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}
