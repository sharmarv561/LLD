package ATM_DESIGN.ChainofResponsibilt;

public class HundredDispenser extends AbstractDispenseChain {
    public HundredDispenser(int availableNotes) {
        super(100, availableNotes);
    }
}
