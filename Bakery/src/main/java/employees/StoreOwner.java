package employees;

import store.CashRegister;
import store.Inventory;

/**
 *
 * A store owner mostly acts as a middle man between certain entities (i.e., cashier, cash register, inventory)
 *
 */
public class StoreOwner {

    private Inventory inventory;
    private CashRegister cashRegister;

    public StoreOwner(Inventory inventory, CashRegister cashRegister){
        this.cashRegister = cashRegister;
        this.inventory = inventory;
    }

    public void addToMenu(String pastry) { this.inventory.addToMenu(pastry.toLowerCase()); }

    public void removeFromMenu(String pastry) { this.inventory.removeFromMenu(pastry.toLowerCase()); }

    public Integer getPrice(String pastry) { return this.inventory.getPrice(pastry.toLowerCase()); }

    public Integer getCount(String pastry) { return this.inventory.getCount(pastry.toLowerCase());}

    public void setPrice(String pastry, Integer price) {
        this.inventory.setPrice(pastry.toLowerCase(), price);
    }

    public void addToInventory(String pastry, Integer quantity){
        this.inventory.addToInventory(pastry.toLowerCase(), quantity);
    }

    public void subtractFromInventory( String pastry, Integer quantity){
        this.inventory.subtractFromInventory(pastry.toLowerCase(), quantity);
    }

    void printMenu() {this.inventory.printMenu();}

    public boolean checkMenu(String pastry) {return this.inventory.checkMenu(pastry.toLowerCase());}

    /**
     * We expect this method to be called by the Cashier when a customer has paid for an item.
     * @param paymentAmount the amount that should be added to the cash register
     */
    public void receivePayment(Integer paymentAmount) {
        this.cashRegister.addCashToRegister(paymentAmount);
    }

    /**
     * This is a getter method that returns the amount of money in the cash register at the moment
     * @return the amount of money in the cash regsiter
     */
    public Integer getCashAmountInRegister(){
        return this.cashRegister.getCashAmountInRegister();
    }
}