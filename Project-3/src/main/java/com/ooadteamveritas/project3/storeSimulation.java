/*
    Simulation Requirements:
    1. Simulate activity for 34 nights
    2. There are 12 customers
    3. Store has 24 different tool to rent, spread across 5 different categories
    4. Each day, random number of customers visit store (if tools to rent)
    5. Each customer creates 1 record per rental period
    6. No customer will show up then leave without making rental
*/

package com.ooadteamveritas.project3;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class storeSimulation {
    
    private int simulationNights;
    private int numCustomers;
    private int numTools;
    private Vector<Customer> rentalCustomers;
    private Store rentalStore ;
    private SimpleToolFactory toolFactory;
    
    //simNight = 34, numCustomerTypes = 4 because (4 * 3 = 12)
    public storeSimulation(int simNights, int numCustomerTypes){
        this.simulationNights = simNights;
        this.numCustomers = numCustomers;     
        this.rentalCustomers = new Vector<Customer>();
        this.toolFactory = new SimpleToolFactory();
        
        
        //Create an insance of store (singleton)
        rentalStore = Store.getInstance();
        
        //Add our customers (using numCustomerTypes)
        for(int i=0;i<numCustomerTypes;i++){
            rentalCustomers.add(new CasualCustomer("Casual Customer #" + i));
            rentalCustomers.add(new RegularCustomer("Regualr Customer #" + i));
            rentalCustomers.add(new BusinessCustomer("Business Customer #" + i));
        }
        numCustomers = numCustomerTypes * 3;
        
        //make tools and put them into the store (24 total)
        for(int i=0;i<5;i++){
            Tool newTool = toolFactory.createTool("paint", "Paint tool" + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("contrete", "Concrete tool" + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("plumbing", "Plumbing tool" + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("woodwork", "Woodwork tool" + Integer.toString(i));
            rentalStore.addTool(newTool);
        }
        //Add 4 more
        for(int i=0;i<4;i++){
            Tool newTool = toolFactory.createTool("yardwork", "Yardwork tool" + Integer.toString(i));
            rentalStore.addTool(newTool);
        }        
    }
    
    public void runSimulation(){   
        
    }
    
    //min -> inclusive
    //max -> inclusive
    public int genRandomNum(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
}