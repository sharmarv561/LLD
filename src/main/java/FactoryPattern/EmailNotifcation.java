package FactoryPattern;

public class EmailNotifcation implements Notification{

    @Override
    public void send(String message){
        System.out.println("Sending Email:"+message);
    }
}
