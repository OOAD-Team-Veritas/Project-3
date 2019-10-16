package com.ooadteamveritas.project3;

import java.util.concurrent.ThreadLocalRandom;

public class BusinessCustomer extends Customer {
    public BusinessCustomer(String name) {
        this.name = name;
        this.hasActiveRental = false;
        this.custType = "business";
    }
    
    @Override
    public void rent() {
        
    }
}
