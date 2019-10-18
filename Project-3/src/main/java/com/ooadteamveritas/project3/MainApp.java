package com.ooadteamveritas.project3;

public class MainApp {
    
    public static void main(String[] args){
        System.out.println("This is the main function!");
        //simNight = 34, numCustomerTypes = 4 because (4 * 3 = 12)
        storeSimulation sim = new storeSimulation(34,4);
        //sim.runSimulation();
        System.out.println("End of Simulation!");
    }
}
