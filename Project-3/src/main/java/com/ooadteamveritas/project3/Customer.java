package com.ooadteamveritas.project3;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

/*
    The count of tools is determined by looking at the record...
*/

public abstract class Customer implements Observer{
    protected Boolean knowsToolsLeft;
    protected String name;
    protected Record record;
    public boolean hasActiveRental;
    protected String custType;
    Observable observable;
    protected int maxTools;
    
    public String getName() {
        return this.name;
    }
    
    public String getCustType(){
        return this.custType;
    }

    public int getMaxTools(){
        return this.maxTools;
    }
    
    //Set set this every rental period
    public void setActiveRecord(Record record){
        this.record = record;
    }

    public Record getCustomerRecord(){
        return record;
    }

    //Observer update function - customer knows if there is inventory left
    public void update(Observable observable, Object arg){
        if(observable instanceof Store){
            Store store = (Store)observable;
            if(store.howManyAvailToolsToRent() < 1){
                this.knowsToolsLeft = false;
            }else{
                this.knowsToolsLeft = true;
            }
        }
    }
    public Boolean getKnowsToolsLeft(){
        return this.knowsToolsLeft;
    }


    //When Customer is done with a rental transaction
    public void clearRecord(){
        this.record = null;
        hasActiveRental = false;
    }
    
    //Returns how many tools customer is renting right now  (from their record)
    public int howManyToolsRented(){
        return record.getRentedTools().size();
    }

    //They can rant 0 - 6 options
    public int howManyOptionsToRent(){
        return this.genRandomNum(0,6);
    }

    //min -> inclusive
    //max -> inclusive
    protected int genRandomNum(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    
    /*
        Generates n random number from 1-3
        where:

        1 -> Extension Cord
        2 -> AccessoryKit
        3 -> Protective Gear Package

        It sets the appropriate count in the option classes
        in the record object
    */
    public void pickOptionsToRent(int n){
        int randomNum;
        for(int i=0; i < n; i++){
            randomNum = genRandomNum(0, 2);
            record.addStoreOption(randomNum);
        }
    }

    //Returns how many tools customer wants to rent
    public abstract int howManyToolsToRent();
    public abstract int rentDuration();
}
