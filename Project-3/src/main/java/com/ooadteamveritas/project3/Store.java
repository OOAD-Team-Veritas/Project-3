package com.ooadteamveritas.project3;
import java.util.*;

//Observable class implements the observable functions not seen here:
//reference: https://www.javaworld.com/article/2077258/observer-and-observable.html

public class Store extends Observable {
    private static Store uniqueInstance;
    private boolean isInventory;
    private Vector<Tool> inventory = new Vector<Tool>();
    private Vector<Record> currentRentalRecords = new Vector<Record>();
    private Vector<Record> pastRentalRecords = new Vector<Record>();
    
    private Store(){}
    
    public static Store getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Store();
        }
        return uniqueInstance;
    }
    public Vector<Tool> getInventory(){
        return this.inventory;
    }
    public void startRental(Record record, Vector<Tool> tools){
        this.currentRentalRecords.add(record);
        tools.forEach((tool) -> this.inventory.remove(tool));
        if(inventory.isEmpty()){
            setValue(false);
        }
    }
    public void addtoRental(Record record, Vector<Tool> tools){
        record.addRentedTools(tools);
        tools.forEach((tool) -> this.inventory.remove(tool));
        if(inventory.isEmpty()){
            setValue(false);
        }
    }
    public void endRental(Record record){
        this.currentRentalRecords.remove(record);
        this.pastRentalRecords.add(record);
        record.getRentedTools().forEach((tool) -> this.inventory.add(tool));
    }
    public double calculateCost(Vector<?> vector){
        
    }
    public double getOptionPrice(int option){//Maybe this should be in Store class?
        switch(option){
            case 1 : //extension cord
                return 2.25;
            case 2 : //accessory kit
                return 5.09;
            case 3 : //protective gear package
                return 9.99;
            
        }
    }
    public void setValue(boolean n){
        this.isInventory = n;
        setChanged();
        notifyObservers();
    }
    public boolean getValue(){
        return this.isInventory;
    }
}


//Example of Java Observable implementation
//import java.util.Observable;
//public class ObservableValue extends Observable
//{
//   private int n = 0;
//   public ObservableValue(int n)
//   {
//      this.n = n;
//   }
//   public void setValue(int n)
//   {
//      this.n = n;
//      setChanged();
//      notifyObservers();
//   }
//   public int getValue()
//   {
//      return n;
//   }
//}

