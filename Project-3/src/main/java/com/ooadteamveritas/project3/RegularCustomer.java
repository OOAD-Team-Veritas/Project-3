/*
    1. Regualar customers rent 1-3 tools
    2. Regualar customers always rent 3-5 days
*/

package com.ooadteamveritas.project3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name) {
        int numTools = ThreadLocalRandom.current().nextInt(1, 4);
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "regular";
    }
    
    public int howManyToolsToRent(){
        return this.genRandomNum(1,3);
    } 

    public int rentDuration(){
        return this.genRandomNum(3,5);
    }
}
