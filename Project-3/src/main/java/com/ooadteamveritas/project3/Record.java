package com.ooadteamveritas.project3;
import java.util.*;

public class Record{
    private Vector<Tool> rentedTools = new Vector<Tool>();
    
    private int nightsUntilDue;
    private Customer customer;
    private double totalCost;
    public int recordId;            //Unique record ID
    
    public Record(int num){
        totalCost = 0;
        recordId = num;
    }
    
    public Vector<Tool> getRentedTools(){
        return this.rentedTools;
    }
    public void addRentedTools(Vector<Tool> tools){
        this.rentedTools.addAll(tools);
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
    
    //Overriding toString() Method
    public String toString() {
       StringBuffer sb = new StringBuffer();
       sb.append("*****Record #" + recordId + "*****\n");
       sb.append("Customer name: ");
       return sb.toString();
    }
}

