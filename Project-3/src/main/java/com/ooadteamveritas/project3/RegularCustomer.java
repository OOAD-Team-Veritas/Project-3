package com.ooadteamveritas.project3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name) {
        int numTools = ThreadLocalRandom.current().nextInt(1, 4);
        tools = new Tool[numTools];
        this.name = name;
    }
    
    @Override
    public void rent() {

    }
}
