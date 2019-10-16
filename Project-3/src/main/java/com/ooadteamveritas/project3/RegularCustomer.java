<<<<<<< HEAD
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
    
    @Override
    public void rent() {

    }
}
=======
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
    }
    
    public int howManyToolsToRent(){
        return this.genRandomNum(1,3);
    } 

    public int rentDuration(){
        return this.genRandomNum(3,5);
    }
}
>>>>>>> b0ce34ca3f096f348442b4160c44d7d83d574a9d
