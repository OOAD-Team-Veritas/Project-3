package com.ooadteamveritas.project3;
import java.util.*;

public class Record{
    private Vector<Tool> rentedTools = new Vector<Tool>();
    private int nightsUntilDue;
    private Customer customer;
    
    public Vector<Tool> getRentedTools(){
        return this.rentedTools;
    }
    public void addRentedTool(Tool tool){
        this.rentedTools.add(tool);
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

