package AbstractFactoryPattern;

public class MacOsCheckBox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("mac checkbox painted");
    }

    @Override
    public void onSelect() {
        System.out.println("Mac checkbox selected");
    }
}
