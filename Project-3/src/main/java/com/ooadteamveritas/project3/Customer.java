package com.ooadteamveritas.project3;
import java.util.concurrent.ThreadLocalRandom;

/*
    The count of tools is determined by looking at the record...
*/

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

    public Record getCustomerRecord(){
        return record;
    }
    
    //When Customer is done with a rental transaction
    public void clearRecord(){
        this.record = null;
    }
    
    //Returns how many tools customer is renting right now  (from their record)
    public int howManyToolsRented(){
        return record.rentedTools.size();
    }

    //min -> inclusive
    //max -> inclusive
    protected int genRandomNum(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    //Returns how many tools customer wants to rent
    public abstract int howManyToolsToRent();
    public abstract int rentDuration();
}
