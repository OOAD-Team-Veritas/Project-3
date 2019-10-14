package com.ooadteamveritas.project3;
import java.util.*;

public class Record{
    private Vector<Tool> rentedTools = new Vector<Tool>();
    private Vector<int> addedOptions = new Vector<int>();
    private int nightsUntilDue;
    private Customer customer;
    private double totalCost = 0;
    
    public Vector<Tool> getRentedTools(){
        return this.rentedTools;
    }
    public void addRentedTools(Vector<Tool> tools){
        this.rentedTools.addAll(tools);
    }
    public void addOptions(Vector<String> addOns){
        this.addedOptions.addAll(addOns);
    }
    public void updateTotalCost(double total){
        this.totalCost = this.totalCost + total;
    }
    public double getTotalCost(){
        return this.totalCost;
    }
    public int getNightsUntilDue(){
        return this.nightsUntilDue;
    }
    public void setNightsUntilDue(int nights){
        this.nightsUntilDue = nights;
    }
    public void decrementNightsUntilDue(){
        this.nightsUntilDue = this.nightsUntilDue-1;
    }
    public Customer getCustomer(){
        return this.customer;
    }
 
}

