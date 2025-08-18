package AbstractFactoryPattern;

public class Application {
    private final Button button;
    private final CheckBox checkBox;

    public Application(GUIFactory guiFactory){
        this.button = guiFactory.createButton();
        this.checkBox = guiFactory.createCheckBox();
    }

    public void renderUI() {
        button.paint();
        button.onClick();

        checkBox.paint();
        checkBox.onSelect();
    }
}
