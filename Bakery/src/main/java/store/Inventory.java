package store;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to be utilized by the store Owner.
 * The store Owner is the only employee with access to the inventory
 */
public class Inventory {

    private Map<String, Integer> inventoryMap = new HashMap<>();
    private Map<String, Integer> priceMap = new HashMap<>();

    public void addToMenu(String pastry) {
        inventoryMap.put(pastry, 0);
        priceMap.put(pastry, 0);
    }

    public void removeFromMenu(String pastry) {
        if (this.inventoryMap.get(pastry).equals(0)) {
            this.inventoryMap.remove(pastry, 0);
            this.priceMap.remove(pastry, 0);

        } else
            System.out.println("There are still " + pastry + "s in stock.");
    }

    public boolean checkMenu(String pastry) {return this.inventoryMap.containsKey(pastry);}

    public void setPrice(String pastry, Integer price) { this.priceMap.put(pastry, price);}

    public void addToInventory(String pastry, Integer quantity) {
        this.inventoryMap.put(pastry, this.inventoryMap.get(pastry) + quantity);
    }

    public void subtractFromInventory(String pastry, Integer quantityDesired) {
        this.inventoryMap.put(pastry, this.inventoryMap.get(pastry) - quantityDesired);
    }

    public Integer getCount(String pastry) { return this.inventoryMap.get(pastry); }

    public Integer getPrice(String pastry) {return this.priceMap.get(pastry); }

    public void printMenu(){
        for (String key : this.priceMap.keySet()) {
            // System.out.println(key.substring(0, 1).toUpperCase() + key.substring(1) + "   $" + this.priceMap.get(key));

            // Change the numbers after %- to adjust menu spacing
            System.out.printf("%-15s%-15s\n" , key.substring(0, 1).toUpperCase() + key.substring(1) ,"$" + this.priceMap.get(key));
        }
    }
}