/*
    1. Business customers always rent 3 tools 
    2. Business customers always rent for 7 nights
    3. Business customers don't arrive into the store is store has < 3 tools!
*/
package com.ooadteamveritas.project3;

public class BusinessCustomer extends Customer {
    public BusinessCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "business";
    }
    
    public int howManyToolsToRent(){
        return 3;
    } 

    public int rentDuration(){
        return 7;
    }
}
