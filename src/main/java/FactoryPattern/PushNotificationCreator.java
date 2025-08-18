package FactoryPattern;

public class PushNotificationCreator implements NotificationCreator{
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}
