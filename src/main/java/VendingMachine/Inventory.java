package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Product> productMap;   //O(1) access by product code

    public Inventory() {
        this.productMap = new HashMap<>();
    }

    // Add a new product or update quantity if it already exists
    public boolean addProduct(String code, Product product){
        if (productMap.containsKey(code)) {
            System.out.println("Product with code " + code + " already exists. Use replaceProduct to overwrite.");
            return false;
        }
        productMap.put(code,product);
        return true;
    }

    public boolean hasProduct(String code) {
        Product product = productMap.get(code);
        return product != null && product.getQuantity() > 0;
    }

    // Explicitly replace an existing product
    public boolean replaceProduct(String code, Product product) {
        if (!productMap.containsKey(code)) {
            System.out.println("No product with code " + code + " found. Use addProduct to add a new one.");
            return false;
        }
        productMap.put(code, product);
        return true;
    }

    // Get product by code
    public Product getProduct(String code) {
        return productMap.get(code);
    }

    // Check if product is available
    public boolean isAvailable(String code) {
        Product product = productMap.get(code);
        return product != null && product.getQuantity() > 0;
    }

    // Reduce quantity of product after dispensing
    public boolean reduceProduct(String code) {
        Product product = productMap.get(code);
        if (product != null && product.getQuantity() > 0) {
            product.reduceQuantity();
            return true;
        }
        return false;
    }

    public boolean restockProduct(String code, int quantity) {
        Product product = productMap.get(code);
        if (product == null) {
            System.out.println("Product with code " + code + " does not exist.");
            return false;
        }
            product.restock(quantity);
        return true;
    }

    // Get inventory snapshot
    public Map<String, Product> getAllProducts() {
        return productMap;
    }

    //all methods have O(1) TC
/*
I separated addProduct, replaceProduct, and restockProduct to make intentions explicit.
This avoids accidental overwrites and adheres to Single Responsibility Principle (SRP).
For example, restockProduct() doesn’t allow changing price or name — just quantity.
It keeps inventory logic predictable, robust, and easy to debug.
 */

}
