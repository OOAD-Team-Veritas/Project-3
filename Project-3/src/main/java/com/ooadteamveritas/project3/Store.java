package com.ooadteamveritas.project3;
import java.util.*;

//Observable class implements the observable functions not seen here:
//reference: https://www.javaworld.com/article/2077258/observer-and-observable.html

public class Store extends Observable {
    private static Store uniqueInstance;
    private boolean isInventory;
    public ArrayList<Tool> inventory;
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

    public ArrayList<Tool> getInventory(){
        return this.inventory;
    }

    public void startRental(Record record, Vector<Tool> tools){
        this.currentRentalRecords.add(record);
        tools.forEach((tool) -> this.inventory.remove(tool));
        if(inventory.isEmpty()){
            setValue(false);
        }
    }
    /*
    public void addtoRental(Record record, Vector<Tool> tools){
        record.addRentedTools(tools);
        tools.forEach((tool) -> {
            this.inventory.remove(tool);
            
                });
        if(inventory.isEmpty()){
            setValue(false);
        }
    }
    public void endRental(Record record){
        this.currentRentalRecords.remove(record);
        this.pastRentalRecords.add(record);
        record.getRentedTools().forEach((tool) -> this.inventory.add(tool));
    }
    */

    public void setValue(boolean n){
        this.isInventory = n;
        setChanged();
        notifyObservers();
    }
    public boolean getValue(){
        return this.isInventory;
    }
    
    //Adds a tool to the inventory (used by simulation)
    public void addTool(Tool newTool){
        this.inventory.add(newTool);
    }
    
    //Checks if there is available inventoy to rent (at least one)
    public boolean checkIfAvailInventory(){
        boolean avail = false;
        for(Tool tool : inventory){
            if(tool.isRented() == false)
                avail = true;
        }
        return avail;
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

    /*
    ============================================================================
                            selectedNTools
        Gets the first rentable tools from the store inventory and 
        returns as an ArrayList of tools
    ============================================================================
    */
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
        
        if(cust.getCustType() == "business"){
            //We have a business cusotmer...
            if(howManyAvailToolsToRent() < 3){
                result = false;
            }
        }else if(cust.getCustType() == "regular"){
            //We have a regualar cusotmer...
            if(checkIfAvailInventory() == false){
                result = false;
            }  
        }else if(cust.getCustType() == "casual"){
            //We have a casual cusotmer...
            if(checkIfAvailInventory() == false){
                result = false;
            }  
        }
        return result;
    }
}
