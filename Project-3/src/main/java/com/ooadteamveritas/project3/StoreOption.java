package com.ooadteamveritas.project3;

public abstract class StoreOption {
    protected int count;
    protected double cost;
    protected String description;
    
    public int getCount(){
       return count; 
    }
    
    public void setCount(int newCount){
        this.count = newCount;
    }
    
    public void incrementCount(){
        this.count++;
    }
    
    public double getCost(){
        return cost;
    }
    
    public String getDescription(){
        return description;
    }
}
