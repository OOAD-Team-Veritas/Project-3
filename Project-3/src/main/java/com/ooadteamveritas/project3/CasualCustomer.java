package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class CasualCustomer extends Customer {
    public CasualCustomer(String name) {
        int numTools = ThreadLocalRandom.current().nextInt(1, 3);
        tools = new Tool[numTools];
        this.name = name;
    }
    
    @Override
    public void rent() {
                
    }
}
