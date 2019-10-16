/*
    1. Casual customers rent 1-2 tools
    2. Regualar customers always rent 1-2 days
*/

package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class CasualCustomer extends Customer {
    public CasualCustomer(String name) {
        int numTools = ThreadLocalRandom.current().nextInt(1, 3);
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
