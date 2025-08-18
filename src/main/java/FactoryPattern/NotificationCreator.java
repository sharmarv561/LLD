package FactoryPattern;

public interface NotificationCreator {

    Notification createNotification();

    default void send(String message){
        Notification notification = createNotification();
        notification.send(message);

    }
}
