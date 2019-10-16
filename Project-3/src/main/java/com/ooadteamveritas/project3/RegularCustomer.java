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
