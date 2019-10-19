package com.ooadteamveritas.project3;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MainApp {
    
    public static void main(String[] args) throws FileNotFoundException{

        /*
            Learned how to redirect java console output to file using this:
            https://www.dev2qa.com/how-to-write-console-output-to-text-file-in-java/
        */

        // Save original out stream.
        PrintStream originalOut = System.out;
        // Save original error stream.
        PrintStream originalErr = System.err;

        // Create a new file output stream.
        PrintStream fileOut = new PrintStream("./out.txt");
        // Create a new file error stream. 
        PrintStream fileErr = new PrintStream("./err.txt");
        
        // Redirect standard out out.txt.
        System.setOut(fileOut);
        // Redirect standard err to err.txt.
        System.setErr(fileErr);
        //Set up our Zoo

        System.out.println("Starting the Rental Store simulation...");
        //simNight = 34, numCustomerTypes = 4 because (4 * 3 = 12)
        storeSimulation sim = new storeSimulation(34,4);
        sim.runSimulation();
        System.out.println("End of Simulation!");
    }
}
