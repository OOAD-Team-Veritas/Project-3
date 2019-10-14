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
    public void startRental(Record record, Tool tool){
        this.currentRentalRecords.add(record);
        this.inventory.remove(tool);
        
    }
    public void endRental(Record record, Tool tool){
        this.currentRentalRecords.remove(record);
        this.pastRentalRecords.add(record);
        this.inventory.add(tool);
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

