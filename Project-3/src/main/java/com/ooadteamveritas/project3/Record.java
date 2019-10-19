package com.ooadteamveritas.project3;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Record{
    private int nightsUntilDue;
    private int rentDuration;
    private Customer customer;       //Customer associated with this record   
    private double totalCost;
    private int recordId;            //Unique record ID
    private ArrayList<Tool> rentedTools = new ArrayList<Tool>();

    //The options live here (only one instance each with a count for quantity)
    private ArrayList<StoreOption> options = new ArrayList<StoreOption>();
    
    //Constuctor
    public Record(){
        totalCost = 0;
        rentDuration = 0;
        nightsUntilDue = 0;

        //Add the options
        options.add(new ExtensionCord());
        options.add(new AccessoryKit());
        options.add(new ProtectiveGearPackage());
    }
    
    public void addRentedTools(ArrayList<Tool> tools){
        this.rentedTools.addAll(tools);
        updateTotalCost(tools, null);
    }

    public ArrayList<Tool> getRentedTools(){
        return this.rentedTools;
    }

    public void addStoreOption(int whichOption){
        StoreOption option = this.options.get(whichOption);
        option.count++;
        updateTotalCost(null, option);
    }

    public ArrayList<StoreOption> getRentedOptions(){
        return this.options;
    }

    public void setCustomer(Customer cust){
        this.customer = cust;
    }

    public Customer getCustomer(){
        return this.customer;
    }
    
    //Returns amount formatted as currency as a string
    private String formatAsCurrency(double amount){
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		String result = defaultFormat.format(amount);
        return result;
    }
    
    public void updateTotalCost(ArrayList<Tool> tools, StoreOption option){
        double total = 0;
        if(tools != null){
            for(Tool tool : tools){
                total += tool.price * this.getNightsUntilDue();
            }
        }
        if(option != null){
            total += option.getCost(); 
        }
        this.totalCost += total;
    }
    
    public double getTotalCost(){
        return this.totalCost;
    }

    public String getTotalCostFormatCurr(){
        return formatAsCurrency(totalCost);
    }
    
    //Sets the nightsUntilDue counter and the rentDuration var
    public void setNightsUntilDue(int nights){
        this.nightsUntilDue = nights;
        this.rentDuration = nights;

    }

    public void decrementNightsUntilDue(){
        this.nightsUntilDue--;
    }

    public int getNightsUntilDue(){
        return this.nightsUntilDue;
    }

    public int getRentDuration(){
        return this.rentDuration;
    }
    
    //Overriding toString() Method
    public String toString() {
       StringBuffer sb = new StringBuffer();
       sb.append("*****Record #" + recordId + "*****\n");
       sb.append("Customer name: ");
       return sb.toString();
    }
}

