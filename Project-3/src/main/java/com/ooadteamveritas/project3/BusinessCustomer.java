/*
    1. Business customers always rent 3 tools 
    2. Business customers always rent for 7 nights
    3. Business customers don't arrive into the store is store has < 3 tools!
*/
package com.ooadteamveritas.project3;

import java.util.Observable;

public class BusinessCustomer extends Customer {
    public BusinessCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "business";
        this.maxTools = 3;
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable instanceof Store){
            Store store = (Store)observable;
            if(store.howManyAvailToolsToRent() < 3){
                this.knowsToolsLeft = false;
            }else{
                this.knowsToolsLeft = true;
            }
        }
    }
    
    public int howManyToolsToRent(){
        return 3;
    }

    public int rentDuration(){
        return 7;
    }
}
