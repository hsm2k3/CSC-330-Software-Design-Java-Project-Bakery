// Any classes in src/test/java are treated in a special way by Maven.
// Although this file technically lives in src/test/java/store,
// Maven automatically makes any files in  src/main/java/store available to us.
// This means our BakeryTest class is essentially in the the same 'store' package as Bakery.java,
// even though these two files live in different places on our filesystem.

package store;

import misc.Customer;

/**
 * This is our driver code that should test our full Bakery implementation
 */
public class BakeryTest {

    public static void main(String[] args) {

        // Create a new Bakery.
        Bakery bakery = new Bakery();

        // We are directly modifying certain things in the Bakery for testing purposes.
        // This is only possible because certain fields (i.e., storeOwner, inventory) are declared as package private
        // in the bakery class.  Our current class is also considered to be in the same 'store' package.
        // See the comment at the top of this file.

        bakery.storeOwner.addToMenu("Cake");
        bakery.storeOwner.addToMenu("Pie");
        bakery.storeOwner.addToMenu("Muffin");
        bakery.storeOwner.addToMenu("Cookie");

        bakery.storeOwner.setPrice("Cake",16);
        bakery.storeOwner.setPrice("Pie", 10);
        bakery.storeOwner.setPrice("Muffin", 8);
        bakery.storeOwner.setPrice("Cookie", 4);

        bakery.storeOwner.addToInventory("Cake", 3);
        bakery.storeOwner.addToInventory("Pie", 3);
        bakery.storeOwner.addToInventory("Muffin", 3);
        bakery.storeOwner.addToInventory("Cookie", 3);

        // Create a new customer named Bob with some money in his wallet
        Customer customer = new Customer("Bob", 100);

        // Begin the interaction with Bob
        bakery.visit(customer);

        bakery.storeOwner.getCashAmountInRegister();
    }
}