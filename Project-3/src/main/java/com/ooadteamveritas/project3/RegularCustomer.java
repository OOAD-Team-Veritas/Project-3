/*
    1. Regualar customers rent 1-3 tools
    2. Regualar customers always rent 3-5 days
*/
package com.ooadteamveritas.project3;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "regular";
        this.maxTools = 3;
    }
    
    public int howManyToolsToRent(){
        return this.genRandomNum(1,3);
    } 

    public int rentDuration(){
        return this.genRandomNum(3,5);
    }
}
