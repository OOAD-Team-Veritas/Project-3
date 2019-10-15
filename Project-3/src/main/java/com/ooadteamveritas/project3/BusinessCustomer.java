package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class BusinessCustomer extends Customer {
    public BusinessCustomer(String name) {
        tools = new Tool[3];
        this.name = name;
    }
    
    @Override
    public void rent() {
        
    }
}
