package com.ooadteamveritas.project3;
import java.util.ArrayList;
import java.util.Observable;

//Observable class implements the observable functions not seen here:
//reference: https://www.javaworld.com/article/2077258/observer-and-observable.html

public class Store extends Observable {
    private static Store uniqueInstance;
    private boolean isInventory;
    private ArrayList<Tool> inventory;
    private ArrayList<Record> currentRentalRecords;
    private ArrayList<Record> pastRentalRecords;
    
    //Constructor
    private Store(){
        inventory = new ArrayList<Tool>();
        currentRentalRecords = new ArrayList<Record>();
        pastRentalRecords = new ArrayList<Record>();
    }
    
    //Singleton => getInstance
    public static Store getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Store();
        }
        return uniqueInstance;
    }

    /*
    ============================================================================
                                    Inventory
        Includes the Observable functions.
    ============================================================================
    */
    public ArrayList<Tool> getInventory(){
        return this.inventory;
    }

    //Adds a tool to the inventory (used by simulation)
    public void addTool(Tool newTool){
        this.inventory.add(newTool);
    }

    //These two functions implement the Observable class - are there tools in the Store's inventory
    public void setIsInventory(boolean n){
        this.isInventory = n;
        setChanged();
        notifyObservers();
    }

    public boolean getIsInventory(){
        return this.isInventory;
    }

    //Returns the count of tools that can be rented
    public int howManyAvailToolsToRent(){
        int count = 0;
        for(Tool tool : inventory){
            if(tool.isRented() == false)
                count++;
        }
        return count;
    }

    //don't forget to add observer here, this function shouldn't be included in the end product
    //Checks if there is available inventoy to rent (at least one)
    public void checkIfAvailInventory(){
        boolean avail = false;
        for(Tool tool : inventory){
            if(tool.isRented() == false)
                avail = true;
        }
        if(getIsInventory() != avail){
            setIsInventory(avail);
        }
    }

    /*
    ============================================================================
                            Current and Past Records
    ============================================================================
    */
    public ArrayList<Record> getCurrentRentalRecords(){
        return this.currentRentalRecords;
    }

    public ArrayList<Record> getPastRentalRecord(){
        return this.pastRentalRecords;
    }
    //Algorithm for ending a rental and cleaning up Records
    public void endRental(Record record){
        this.currentRentalRecords.remove(record);
        this.pastRentalRecords.add(record);
        record.getCustomer().clearRecord();
        for(Tool tool: record.getRentedTools()){
            tool.rentedOut = false;
        }
    }

    /*
    ============================================================================
                            selectedNTools
        Gets the first rentable tools from the store inventory and 
        returns as an ArrayList of tools
    ============================================================================
    */

    ////add checkifAvailabletools here to check each time after tool.rent()
    public ArrayList<Tool> selectedNTools(int n){
        int count = 0;
        ArrayList<Tool> selectedTools = new ArrayList<Tool>();

        for(Tool tool : inventory){

            if(tool.rentedOut == false){
                if(count < n){
                    count++;
                    selectedTools.add(tool);
                }     
            }
        }
        return selectedTools;
    }

    /*
    ============================================================================
                            canCustomerEnterStore
    ============================================================================
    */

    public boolean canCustomerEnterStore(Customer cust){
        boolean result = true;

        //Check if cust rented the max amount of tools (if they already have a record)
        if(cust.hasActiveRental == true){
            if(cust.howManyToolsRented() >= 3){
                result = false;
            }
        }
        if (cust.getKnowsToolsLeft() != true){
            result = false; 
        }
        return result;
    }
}
