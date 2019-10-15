/*
    Simulation Requirements:
    1. Simulate activity for 34 nights
    2. Each day, random number of customers visit store (if tools to rent)
    3. Each customer creates 1 record per rental period
    4. No customer will show up then leave without making rental
*/

package com.ooadteamveritas.project3;
import java.util.Vector;

public class storeSimulation {
    
    private int simulationNights;
    private Vector<Customer> toolRentalCustomers;
    
    
    public storeSimulation(){
        simulationNights = 1;
    }
    
    public void runSimulation(){
        
        //Create an insance of store (singleton)
        Store rentalStore = Store.getInstance();
        
    }
}
//make customers
//make tools
//make options

