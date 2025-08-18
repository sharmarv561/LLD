package FactoryPattern;

public class EmailNotificationCreator implements NotificationCreator{


    @Override
    public Notification createNotification() {
        return new EmailNotifcation();
    }
}
