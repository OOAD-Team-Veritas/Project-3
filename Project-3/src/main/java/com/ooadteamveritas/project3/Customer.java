package com.ooadteamveritas.project3;

public abstract class Customer {
    protected String name;
    protected Record record;
    public boolean hasActiveRental;
    protected String custType;
    
    public String getName() {
        return this.name;
    }
    
    public String getCustType(){
        return this.custType;
    }
    
    //Set set this every rental period
    public void setActiveRecord(Record record){
        this.record = record;
    }
    
    //When Customer is done with a rental transaction
    public void clearRecord(){
        this.record = null;
    }
    
    //Returns how many tools customer is renting right now
    public int howManyToolsRented(){
        return record.rentedTools.size();
    }
    
    public abstract void rent();
}
