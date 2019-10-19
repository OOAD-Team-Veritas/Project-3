/*
    1. Casual customers rent 1-2 tools
    2. Regualar customers always rent 1-2 days
*/

package com.ooadteamveritas.project3;

public class CasualCustomer extends Customer {
    public CasualCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "casual";
    }

    public int howManyToolsToRent(){
        return this.genRandomNum(1,2);
    } 

    public int rentDuration(){
        return this.genRandomNum(1,2);
    } 
}
