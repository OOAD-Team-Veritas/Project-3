package com.ooadteamveritas.project3;
import java.util.ArrayList;

public class Record{
    public ArrayList<Tool> rentedTools = new ArrayList<Tool>();
    
    private int nightsUntilDue;
    private Customer customer;       //Customer associated with this record   
    private double totalCost;
    public int recordId;            //Unique record ID
    
    //The options live here (only one instace)
    private ArrayList<StoreOption> options = new ArrayList<StoreOption>();
    
    //Constuctor
    public Record(){
        totalCost = 0;
        
        //Add the options
        options.add(new ExtensionCord());
        options.add(new AccessoryKit());
        options.add(new ProtectiveGearPackage());
    }
    
    public ArrayList<Tool> getRentedTools(){
        return this.rentedTools;
    }
    
    public void addRentedTools(ArrayList<Tool> tools){
        this.rentedTools.addAll(tools);
    }

    public void setCustomer(Customer cust){
        this.customer = cust;
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

