package com.ooadteamveritas.project3;
import java.util.ArrayList;
import java.util.Vector;

public class Record{
    public Vector<Tool> rentedTools = new Vector<Tool>();
    
    private int nightsUntilDue;
    private Customer customer;       //Customer associated with this record   
    private double totalCost;
    public int recordId;            //Unique record ID
    
    //The options live here (only one instace)
    private ArrayList<StoreOption> options = new ArrayList<StoreOption>();
    
    //Constuctor
    public Record(int num){
        totalCost = 0;
        recordId = num;
        
        //Add the options
        options.add(new ExtensionCord());
        options.add(new AccessoryKit());
        options.add(new ProtectiveGearPackage());
    }
    
    public Vector<Tool> getRentedTools(){
        return this.rentedTools;
    }
    
    public void addRentedTools(ArrayList<Tool> tools){
        this.rentedTools.addAll(tools);
        updateTotalCost(tools, null);
    }
    public void addStoreOption(int whichOption){
        StoreOption option = this.options.get(whichOption);
        option.count++;
        updateTotalCost(null, option);
    }
    
    public void updateTotalCost(ArrayList<Tool> tools, StoreOption option){
        float total = 0;
        if(tools != null){
            for(Tool tool : tools){
                total += tool.price * this.getNightsUntilDue();
            }
        }
        if(option != null){
            total += option.getCost();
        }
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