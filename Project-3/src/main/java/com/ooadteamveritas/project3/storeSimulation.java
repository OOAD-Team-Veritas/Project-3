/*
    Simulation Requirements:
    1. Simulate activity for 34 nights
    2. There are 12 customers
    3. Store has 24 different tool to rent, spread across 5 different categories
    4. Each day, random number of customers visit store (if tools to rent)
    5. Each customer creates 1 record per rental period
    6. No customer will show up then leave without making rental (observer stuff?)
    7. Each customer rents a max of 3 tools no matter what category they belong to!
    8. 

    * Process each customer one at a time (they might take the tools)

*/

package com.ooadteamveritas.project3;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class storeSimulation {
    
    private int simulationNights;       //For testing (how many nights)
    private int numCustomers;               //For testing (how much of each catergory) 
    private ArrayList<Customer> rentalCustomers;    //Our Customers
    private Store rentalStore ; //The rental store with Tools
    ArrayList<Record> todaysReturns;
    private SimpleToolFactory toolFactory;  //Tool factory
    
    
    /*
    ============================================================================
                                Constructor
    ============================================================================
    */
    //simNight = 34, numCustomerTypes = 4 because (4 * 3 = 12)
    public storeSimulation(int simNights, int numCustomerTypes){
        this.simulationNights = simNights;
        this.numCustomers = numCustomers;     
        this.rentalCustomers = new ArrayList<Customer>();
        this.toolFactory = new SimpleToolFactory();
        this.todaysReturns = new ArrayList<Record>();
        
        
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
    
    /*
    ============================================================================
                        The Simulation => runSimulation
    ============================================================================
    */
    public void runSimulation(){
        int todaysCustomerNum = 0;       //Number of customers arriving in a day
        Customer selectedCustomer;
        ArrayList<Customer> selectedDayCustomers = new ArrayList<Customer>();    
        
        //Loop for the nights in the simulation...
        for(int i = 0; i < simulationNights; i++){
            /********************************************************
                Start of Day
            /********************************************************/

            //Check if a record has 0 in daysuntildue
                //If so... then we call end rental in store
                    
            


            ////start of day////
            
            //Clear yesterday's rentals
            todaysReturns.clear();
            //All customers return to store if needed to return tools
            for(Customer customer : rentalCustomers){
                Record record = customer.getCustomerRecord();
                if (record.getNightsUntilDue() == 0){
                    todaysReturns.add(record);
                    rentalStore.endRental(record);
                }
            }
            
            //Generate Random number of customers that will arrive today
            todaysCustomerNum = genTodaysCustNum();
            
            //Pick random customers that will enter the store...
            for(int j = 0; j < todaysCustomerNum; i++){
                //Check if our Arraylist of selected customers in empty...
                if(selectedDayCustomers.isEmpty()){
                    //If empty... add the customer to our selected list
                    selectedDayCustomers.add(getRandomCustomer());
                }else{
                    //We have to check if we're not getting the same customer again
                    selectedCustomer = getRandomCustomer();
                    while(checkIfAlreadySelectedCust(selectedCustomer,selectedDayCustomers)){
                        //Select another one if we already got that one...
                        selectedCustomer = getRandomCustomer();
                    }
                }
                
                //Now we already have our selected customers in selectedDayCustomers
                
                //For every selected customer =>
                for(Customer cust : selectedDayCustomers){
                    
                    
                    //Check if the customer can enter the store... (observer stuff)
                    /*
                        Check what kind of cust is it...
                        Check the amount of tools they already rented
                    */
                    if(rentalStore.canCustomerEnterStore(cust)){
                        
                        //If yes, then we ask customer how many tools the want to rent (method in their classes)
                        //Depending on the customer, we determine how many tools to rent (randomly)
                        int rentDuration = cust.rentDuration();
                        int numOfTools = cust.howManyToolsToRent();
                        int numOfOptions = cust.howManyOptionsToRent();   

                        //Account for situations where customer alreay rented some tools (max is 3...)
                        numOfTools = numOfTools - cust.howManyToolsRented();
                        
                        //Check if the customer already has a active rental, and hence an existing record
                        if(cust.hasActiveRental == false){                            
                        
                            //If not... then create one for them 
                            Record newRecord = new Record();
                            
                            //Set the reference to Customer our new record
                            newRecord.setCustomer(cust);
                            //Set the reference to Record for our customer
                            cust.setActiveRecord(newRecord);
        
                        }
                        //Get the customer's record...
                        Record currentCustomersRecord = cust.getCustomerRecord();

                        //Add the selected tools to the record
                        ArrayList<Tool> pickedTools = rentalStore.selectedNTools(numOfTools);
                        currentCustomersRecord.addRentedTools(pickedTools);
                        
                        //Update Customer's hasActiveRental status (bool)
                        cust.hasActiveRental = true;

                        //Determine what options the customer wants...
                        cust.pickOptionsToRent(numOfOptions);
                        
                        //Calculate the cost of everything...
                        
                        
                    }else{
                        //Customer can't enter store - get another customer...
                        continue;
                    }          
                }
            }
            //Print out all the info
            
            //Go through all records and decrement all daysUntilDue 
            decrementAllRecords();
        }  
    }
    
    //min -> inclusive
    //max -> inclusive
    private int genRandomNum(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    public Customer getRandomCustomer() { 
        //uses index
        int randIndex = genRandomNum(0,11);
        return rentalCustomers.get(randIndex); 
    } 
    
    //Tells you how many customers will arrive in a day
    public int genTodaysCustNum(){
        return genRandomNum(1,12);
    }

    /*
    ============================================================================
                        checkIfAlreadySelectedCust
    ============================================================================
    */
    //Check if we selected a Customer twice (look above)
    public boolean checkIfAlreadySelectedCust(Customer selected, ArrayList<Customer> selectedDayCustomers){
        for(Customer cust: selectedDayCustomers){
            //== compares object references, it checks to see if the two operands point to the same object 
            if(cust == selected)
                return true;
        }
        return false;
    }
    
    private void decrementAllRecords(){
        for(Record record : rentalStore.currentRentalRecords){
            record.decrementNightsUntilDue();
        }
    }
    
    public void printDayReport(int dayNum){
        StringBuffer sb = new StringBuffer();
        sb.append("=========================================================\n");
        sb.append("                     Day# " + dayNum +                  "\n");
        sb.append("=========================================================\n");

        sb.append("\n");
        sb.append("Number of completed rentals today: " + todaysReturns.size());
        sb.append("\n");
        
        //Print all of today's completed rentals and details
        for(int i = 0; i < todaysReturns.size(); i++){
            sb.append("****************************\n");
            sb.append("Todays Return #" + i + "\n");
            sb.append("****************************\n");
            
            //Loop through all of the records in todaysReturns
            for(Record rec : todaysReturns){
                sb.append("Customer name: " + rec.getCustomer().getName() + "\n");
                sb.append("Return duration: " + rec.getRentDuration() + "\n");
                sb.append("Rental Total cost: " + rec.getTotalCost() + "\n");

                sb.append("-------------------\n");
                sb.append("   Rented Tools:   \n");
                sb.append("-------------------\n");

                //Loop through all the tools that were rented
                for(Tool rentedTool : rec.getRentedTools()){
                    sb.append(rentedTool.getName() + "\n");
                }

                sb.append("-------------------\n");
                sb.append("  Rented Options:  \n");
                sb.append("-------------------\n");

                //Loop through all rented options
                for(StoreOption op : rec.getRentedOptions()){
                    //Print how many of that option and then the Description...
                    sb.append(op.getCount() + " " + op.getDescription() + "\n");
                }
                sb.append("\n");
            }          

        }
        sb.append("****************************\n");
        sb.append("     Current Rentals        \n");
        sb.append("****************************\n");
        //Print all active rentals from the store...
        //Loop through all the records in currentRentalRecords...
        for(Record rec : rentalStore.getCurrentRentalRecords()){
            sb.append("Customer name: " + rec.getCustomer().getName() + "\n");
            //Loop through all the tools in the record and print the name of each tool
            for(Tool tool : rec.getRentedTools()){
                //Intendeted to group by customer
                sb.append("     " + tool.getName() + "\n");
            }
        }
        sb.append("\n");
        sb.append("****************************\n");
        sb.append("    Tools In store     \n");
        sb.append("****************************\n");
        sb.append("Available tools in store: " + rentalStore.howManyAvailToolsToRent() + "\n");

        //Loop through the inventory of tools in store and print all the tools that have rentedOut = false
        for(Tool tool : rentalStore.getInventory()){
            if(tool.isRented() == false){
                //Print the name of the tool if isRented is false
                sb.append(tool.getName());
            }
        }

        //Print the total amount of money that the store has made today

        //Prints the whole stringBuffer
        System.out.println(sb);
    }
}