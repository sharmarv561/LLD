package AbstractFactoryPattern;

public class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Mac button painted");
    }

    @Override
    public void onClick() {
        System.out.println("mac button clicked");
    }
}
