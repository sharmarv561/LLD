// ===== Core contract =====
interface Pizza {
    String getDescription();
    double getCost();
}

// ===== Bases =====
enum BaseType {
    THIN_CRUST(200.0),       // example base prices
    CHEESY_CRUST(260.0);

    private final double basePrice;
    BaseType(double price) { this.basePrice = price; }
    public double price() { return basePrice; }
}

class BasePizza implements Pizza {
    private final BaseType base;

    public BasePizza(BaseType base) {
        this.base = base;
    }

    @Override
    public String getDescription() {
        return base.name().replace('_', ' ').toLowerCase();
    }

    @Override
    public double getCost() {
        return base.price();
    }
}

// ===== Size (multiplicative) =====
enum Size {
    SMALL(1.00),
    MEDIUM(1.25); // adjust multipliers as needed

    private final double multiplier;
    Size(double m) { this.multiplier = m; }
    public double m() { return multiplier; }
}

abstract class PizzaDecorator implements Pizza {
    protected final Pizza inner;
    protected PizzaDecorator(Pizza inner) { this.inner = inner; }
}

class SizedPizza extends PizzaDecorator {
    private final Size size;

    public SizedPizza(Pizza inner, Size size) {
        super(inner);
        this.size = size;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", " + size.name().toLowerCase() + " size";
    }

    @Override
    public double getCost() {
        // size multiplies the current running cost
        return inner.getCost() * size.m();
    }
}

// ===== Toppings (additive, with quantity) =====
enum Topping {
    EXTRA_CHEESE(30.0),
    OLIVES(20.0),
    MUSHROOMS(25.0),
    PANEER(35.0),
    CORN(15.0);

    private final double unitPrice;
    Topping(double p) { this.unitPrice = p; }
    public double price() { return unitPrice; }
}

class ToppingDecorator extends PizzaDecorator {
    private final Topping topping;
    private final int quantity; // charge quantity * unit price

    public ToppingDecorator(Pizza inner, Topping topping, int quantity) {
        super(inner);
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be >= 1");
        this.topping = topping;
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        String name = topping.name().toLowerCase();
        return inner.getDescription() + ", " + name + (quantity > 1 ? " x" + quantity : "");
    }

    @Override
    public double getCost() {
        return inner.getCost() + topping.price() * quantity;
    }
}

// ===== Demo =====
public class PizzaShopDemo {
    public static void main(String[] args) {
        // Example 1: Thin crust, Medium, extra cheese x2 + olives x1
        Pizza order1 = new ToppingDecorator(
                new ToppingDecorator(
                        new SizedPizza(
                                new BasePizza(BaseType.THIN_CRUST),
                                Size.MEDIUM),
                        Topping.EXTRA_CHEESE, 2),
                Topping.OLIVES, 1);

        System.out.println("Order 1: " + order1.getDescription());
        System.out.println("Price  : " + order1.getCost());

        // Example 2: Cheesy crust, Small, mushrooms x1 + paneer x2 + corn x3
        Pizza order2 = new ToppingDecorator(
                new ToppingDecorator(
                        new ToppingDecorator(
                                new SizedPizza(
                                        new BasePizza(BaseType.CHEESY_CRUST),
                                        Size.SMALL),
                                Topping.MUSHROOMS, 1),
                        Topping.PANEER, 2),
                Topping.CORN, 3);

        System.out.println("\nOrder 2: " + order2.getDescription());
        System.out.println("Price  : " + order2.getCost());
    }
}
