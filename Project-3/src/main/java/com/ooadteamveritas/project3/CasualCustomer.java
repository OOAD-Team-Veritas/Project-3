package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class CasualCustomer extends Customer {
    public CasualCustomer(String name) {
        int numTools = ThreadLocalRandom.current().nextInt(1, 3);
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "casual";
    }
    
    @Override
    public void rent() {
                
    }
}
