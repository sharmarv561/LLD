package AbstractFactoryPattern;

public class WindowsButton implements Button{

    @Override
    public void paint() {
        System.out.println("Painting in Window button");
    }

    @Override
    public void onClick() {
        System.out.println("Window button clicked");
    }


}
