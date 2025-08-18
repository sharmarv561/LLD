package AbstractFactoryPattern;

public class WindowCheckBox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("Painting a window checkbox");
    }

    @Override
    public void onSelect() {
        System.out.println("Window checkbox selected");

    }
}
