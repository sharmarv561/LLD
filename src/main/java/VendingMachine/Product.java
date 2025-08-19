package VendingMachine;

public class Product {
    private final String code;      // Unique code like A1, B2 etc.
    private final String name;
    private final double price;
    private int quantity;

    public Product(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void restock(int amount) {
        quantity += amount;
    }
}
/*
I kept reduceQuantity() and restock() inside Product because it's the entity that owns the quantity field.
This keeps the class cohesive and prevents any external class from making invalid changes.
If I let Inventory do this directly by modifying quantity, I'd have to expose setters or break encapsulation.
However, Inventory does act as the manager/coordinator — it just delegates stock changes to Product.”
 */