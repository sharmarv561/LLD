package AbstractFactoryPattern;

public class AppLauncher {

    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        GUIFactory factory;

        if(os.contains("mac")){
            factory = new MacOsFactory();
        }
        else {
            factory = new WindowFactory();
        }

        Application app = new Application(factory);
        app.renderUI();
    }
}
