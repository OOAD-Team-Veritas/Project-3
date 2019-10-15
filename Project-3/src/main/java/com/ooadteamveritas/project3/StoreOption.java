package com.ooadteamveritas.project3;

public abstract class StoreOption {
    protected int count;
    protected double cost;
    protected String description;
    
    public int getCount(){
       return count; 
    }
    
    //Gets the total
    public double getValue(){
        return cost * count;
    }
    
    public String getDescription(){
        return description;
    }
}
