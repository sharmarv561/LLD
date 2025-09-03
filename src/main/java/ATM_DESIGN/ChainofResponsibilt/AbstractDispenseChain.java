package ATM_DESIGN.ChainofResponsibilt;

public abstract class AbstractDispenseChain implements DispenseChain {
    protected DispenseChain nextDispenser;
    protected final int denomination;
    protected int availableNotes;

    public AbstractDispenseChain(int denomination, int availableNotes) {
        this.denomination = denomination;
        this.availableNotes = availableNotes;
    }

    @Override
    public void setNextDispenser(DispenseChain nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    @Override
    public synchronized void dispense(int amount) {
        if (amount >= denomination) {
            int notesToDispense = Math.min(amount / denomination, availableNotes);
            int remaining = amount - (notesToDispense * denomination);

            if (notesToDispense > 0) {
                System.out.println("Dispensing " + notesToDispense + " x $" + denomination);
                availableNotes -= notesToDispense;
            }

            if (remaining > 0 && nextDispenser != null) {
                nextDispenser.dispense(remaining);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        }
    }

    @Override
    public synchronized boolean canDispense(int amount) {
        if (amount < 0) return false;
        if (amount == 0) return true;

        int notesToUse = Math.min(amount / denomination, availableNotes);
        int remaining = amount - (notesToUse * denomination);

        return remaining == 0 || (nextDispenser != null && nextDispenser.canDispense(remaining));
    }

    public int getRemainingCash() {
        return denomination * availableNotes + (nextDispenser != null ? ((AbstractDispenseChain) nextDispenser).getRemainingCash() : 0);
    }
}

/*
We mark the class as abstract:
To prevent direct instantiation (it's a base class only),
To enforce proper specialization via subclasses (like HundredDispenser),
And to encourage clean extensibility and design discipline.
 */