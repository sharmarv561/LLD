package FactoryPattern;

public class FactoryMethodMain {

    public static void main(String[] args){

        NotificationCreator creator;

        creator = new EmailNotificationCreator();
        creator.send("This is an email Notification");

        creator = new SmsNotificationCreator();
        creator.send("this is sms");
    }
}
