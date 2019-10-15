package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class CasualCustomer extends Customer {
    public CasualCustomer() {
        int numTools = ThreadLocalRandom.current().nextInt(1, 3);
        tools = new Tool[numTools];
    }
    
    @Override
    public void rent() {
                
    }
}
