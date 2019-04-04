package employees;

import misc.Customer;

import java.util.Scanner;

/**
 * The cashier is the front man for the entire Bakery.
 * The cashier can coordinate with the StoreOwner to get items from inventory and to receive customer payments
 * The Cashier also ask the baker to bake more items
 * The store owner interacts with the inventory as well as the cash register
 */
public class Cashier {

    // We store a reference to the StoreOwner and Baker of the Bakery
    private StoreOwner storeOwner;
    private Baker baker;

    /**
     * Constructor. Initialize our internal reference for StoreOwner
     * @param storeOwner
     */
    public Cashier(StoreOwner storeOwner, Baker baker){
        this.storeOwner = storeOwner;
        this.baker = baker;
    }

    /**
     * When a customer comes to our bakery, this is where the core resides:
     *
     * 1) We greet them using their name.
     * 2) We ask them if they want Cakes (which has an item # of 1) or Muffins (which has an item # of 2)
     * 3) After they've made a selection, we ask them how many of this item they wish to have
     * 4) We will attempt to determine if the bakery even has enough of the item on hand
     *    by asking the StoreOwner about the inventory levels indirectly. Remember that a cashier cannot
     *    manage any aspect of the inventory themselves.
     * 5) If we don't have enough of the item on hand, ask the baker to bake more
     * 6) Once we have enough of the item, determine the payment amount that the customer owes us
     * 6) Have the customer pay us
     * 7) Make sure the customer receives the items they have ordered
     * @param customer
     */
    public void provideService(Customer customer){

        System.out.println("Hello " + customer.getName());

        String item = promptForItem();
        Integer quantityDesired = promptForQuantityDesired(item);

        // Determine how much the customer owes
        Integer paymentAmount = determinePaymentAmount(item, quantityDesired);
        System.out.println("Hello, " + customer.getName() + ", you owe: $" + paymentAmount);


        if (quantityDesired == -1)
            return;

        if (customer.getCashAmountInWallet() < paymentAmount) {
            System.out.println("You don't have enough money, " + customer.getName() + ". Get out of here, you bum!");
            return;
        }

        // Handle the scenario when the bakery does not have enough of the specific item to sell
        if(!hasCorrectItemQuantity(item, quantityDesired)) {
            this.baker.bake(item, quantityDesired + 5);
            System.out.println("Baking " + item + "s to fulfill the order.");
        }

        customer.payCashier(this, paymentAmount);

        receivePayment(paymentAmount);

        // The cashier (this class) asks the store owner to remove a certain number of cakes from the inventory
        this.storeOwner.subtractFromInventory(item, quantityDesired);

        // The cashier (this class) calls the receive() method of the customer reference
        customer.receiveOrder(item, quantityDesired);

    }

    /**
     * This method accepts money from the customer
     * @param paymentAmount the amount we receive from a customer
     */
    public void receivePayment(Integer paymentAmount){
        this.storeOwner.receivePayment(paymentAmount);
    }


    /**
     * Ask the user for an item number
     *
     * @return the item number the selected, which may also be -1 to quit
     */
    private String promptForItem(){
        this.storeOwner.printMenu();
        System.out.println("What can I get you? ");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine().toLowerCase();
        boolean correct = this.storeOwner.checkMenu(selection);

        while(!correct) {
            System.out.println("That is not on the menu. Please choose again.");
            this.storeOwner.printMenu();
            System.out.println("What can I get you? ");
            selection = scanner.nextLine();
            correct = this.storeOwner.checkMenu(selection);
        }
        return selection;
    }

    /**
     * Ask the user for the quantity of the item they wish to purchase
     *
     * @return the item quantity they have specified, which may also be -1 to quit
     */
    private Integer promptForQuantityDesired(String pastry){
        Integer quantityDesired = 0;

        System.out.print("How many " + pastry + "s would you like? [-1 to quit]: ");

        Scanner scanner = new Scanner(System.in);
        quantityDesired = scanner.nextInt();
        return quantityDesired;

    }

    /**
     *  This method delegates to the StoreOwner reference to determine the inventory levels and quantit
     */
    private boolean hasCorrectItemQuantity(String pastry, Integer quantityDesired){
        if(this.storeOwner.getCount(pastry) >= quantityDesired)
            return true;
        else
            return false;
    }

    /**
     * This method delegates to the StoreOwner reference to determine the payment amount owned
     * @param quantityDesired the quantity of an item a customer wants to purchase
     * @return the price associated with some number of cakes or muffins
     */
    private Integer determinePaymentAmount(String pastry, Integer quantityDesired){
        return quantityDesired * this.storeOwner.getPrice(pastry);
    }
}