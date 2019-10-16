/*
    Simulation Requirements:
    1. Simulate activity for 34 nights
    2. There are 12 customers
    3. Store has 24 different tool to rent, spread across 5 different categories
    4. Each day, random number of customers visit store (if tools to rent)
    5. Each customer creates 1 record per rental period
    6. No customer will show up then leave without making rental

    * Process each customer one at a time (they might take the tools)

*/

package com.ooadteamveritas.project3;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class storeSimulation {
    
    private int simulationNights;       //For testing (how many nights)
    private int numCustomers;               //For testing (how much of each catergory) 
    private ArrayList<Customer> rentalCustomers;    //Our Customers
    private Store rentalStore ; //The rental store with Tools
    private SimpleToolFactory toolFactory;  //Tool factory
    
    //simNight = 34, numCustomerTypes = 4 because (4 * 3 = 12)
    public storeSimulation(int simNights, int numCustomerTypes){
        this.simulationNights = simNights;
        this.numCustomers = numCustomers;     
        this.rentalCustomers = new ArrayList<Customer>();
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
        int todaysCustomerNum = 0;       //Number of customers arriving in a day
        Customer selectedCustomer;
        ArrayList<Customer> selectedDayCustomers = new ArrayList<Customer>();
        
        
        //Loop for the nights in the simulation...
        for(int i = 0; i < simulationNights; i++){
            
            //Generate Random number of customers that will arrive today
            todaysCustomerNum = genTodaysCustNum();
            
            //Pick random customers that will enter the store...
            for(int j = 0; j < todaysCustomerNum; i++){
                            
                //Check if our Arraylist of selected customers in empty...
                if(!selectedDayCustomers.isEmpty()){
                    //If empty... add the customer to out selected list
                    selectedDayCustomers.add(getRandomCustomer());
                }else{
                    //We have to check if we're not getting the same customer
                    selectedCustomer = getRandomCustomer();
                    while(checkIfAlreadySelected(selectedCustomer,selectedDayCustomers)){
                        //Select another one if we already got that one...
                        selectedCustomer = getRandomCustomer();
                    }
                }
                
                //Now we already have our selected customers in selectedDayCustomers
                
                //For every selected customer =>
                for(Customer cust : selectedDayCustomers){
                    
                    
                    //Check if the customer can enter the store... (observer stuff)
                    if((cust.howManyToolsRented() < 3 ) && canCustomerEnterStore(cust)){
                        
                        //If yes, then we look at what kind of customer it is...                       
                        
                        //Depending on the customer, we determine how many tools to rent (randomly)

                        //Check if the customer already has a record
                            //If not... then create one for them and add referecnes 

                        //Add the selected tools to the record

                        //Update Customer's count of rented tools
                        
                    }
            
                }
            }
            
        }  
    }
    
    //min -> inclusive
    //max -> inclusive
    private int genRandomNum(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    public Customer getRandomCustomer() { 
        int randIndex = genRandomNum(0,26);
        return rentalCustomers.get(randIndex); 
    } 
    
    //Tells you how many customers will arrive in a day
    public int genTodaysCustNum(){
        return genRandomNum(0,12);
    }
    
    //Check if we selected a Customer twice (look above)
    public boolean checkIfAlreadySelected(Customer selected, ArrayList<Customer> selectedDayCustomers){
        for(Customer cust: selectedDayCustomers){
            //== compares object references, it checks to see if the two operands point to the same object 
            if(cust == selected)
                return true;
        }
        return false;
    } 
    
    //Might be overkill... Made it more "specific" just in case we need to add more stuff
    private boolean canCustomerEnterStore(Customer cust){
        boolean result = true;
        if(cust.getCustType() == "business"){
            //We have a business cusotmer...
            if(rentalStore.howManyAvailToolsToRent() < 3){
                result = false;
            }
        }else if(cust.getCustType() == "regular"){
            //We have a regualar cusotmer...
            if(rentalStore.checkIfAvailInventory() == false){
                result = false;
            }  
        }else if(cust.getCustType() == "casual"){
            //We have a casual cusotmer...
            if(rentalStore.checkIfAvailInventory() == false){
                result = false;
            }  
        }
        return result;
    }
}