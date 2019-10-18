/*
    1. Casual customers rent 1-2 tools
    2. Regualar customers always rent 1-2 days
*/

package com.ooadteamveritas.project3;

import java.util.Observable;

public class CasualCustomer extends Customer {
    public CasualCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "casual";
    }
    
    @Override
    public void update(Observable store, Object numTools) {
        this.willShop = !((int)numTools < 1);
    }

    public int howManyToolsToRent(){
        return this.genRandomNum(1,2);
    } 

    public int rentDuration(){
        return this.genRandomNum(1,2);
    } 
}
