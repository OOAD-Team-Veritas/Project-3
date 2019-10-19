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
        for(int i=1;i<=numCustomerTypes;i++){
            rentalCustomers.add(new CasualCustomer("Casual Customer #" + i));
            rentalCustomers.add(new RegularCustomer("Regular Customer #" + i));
            rentalCustomers.add(new BusinessCustomer("Business Customer #" + i));
        }
        numCustomers = numCustomerTypes * 3;
        //printSelectedCustomers(rentalCustomers);
        
        //make tools and put them into the store (24 total)
        for(int i=1;i<=5;i++){
            Tool newTool = toolFactory.createTool("paint", "Paint tool " + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("concrete", "Concrete tool " + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("plumbing", "Plumbing tool " + Integer.toString(i));
            rentalStore.addTool(newTool);
            newTool = toolFactory.createTool("woodwork", "Woodwork tool " + Integer.toString(i));
            rentalStore.addTool(newTool);
        }
        //Add 4 more
        for(int i=1;i<=4;i++){
            Tool newTool = toolFactory.createTool("yardwork", "Yardwork tool " + Integer.toString(i));
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
        
        //Loop for the nights in the simulation... (main loop)
        for(int i = 0; i < simulationNights; i++){
            //Clear today's revenue
            rentalStore.clearDayRevenue();

            //Clear yesterday's rental loop
            todaysReturns.clear();
            
            //All customers return to store if needed to return tools
            for(Customer customer : rentalCustomers){

                //Check if the customer has an active rental
                if(customer.hasActiveRental == true){
                    //System.out.println(customer.getName() + " has an active rental!");
                    //System.out.println("NightsUntilDue = " + customer.getCustomerRecord().getNightsUntilDue());
                    Record record = customer.getCustomerRecord();
                    if (record.getNightsUntilDue() == 0){
                        todaysReturns.add(record);
                        rentalStore.endRental(record);
                    }
                }       
            }
            
            //Print the report at the start of the day... (passing in the Day#)
            printDayReport(i);
            
            //Generate Random number of customers that will arrive today
            todaysCustomerNum = genTodaysCustNum();
            
            //System.out.println("Today there are " + todaysCustomerNum  + " Customers entering the store");
            
            //Pick random customers that will enter the store...
            for(int j = 0; j < todaysCustomerNum; j++){
                //Clear the customers that in the store last time

                selectedDayCustomers.clear();
                //Check if our Arraylist of selected customers in empty...
                if(selectedDayCustomers.isEmpty()){
                    //If empty... add the customer to our selected list
                    selectedDayCustomers.add(getRandomCustomer());
                }else{
                    //We have to check if we're not getting the same customer again
                    selectedCustomer = getRandomCustomer();
                    while(checkIfAlreadySelectedCust(selectedCustomer,selectedDayCustomers) == true){
                        //Select another one if we already got that one...
                        selectedCustomer = getRandomCustomer();
                    }
                    selectedDayCustomers.add(selectedCustomer);
                }
                
                //Now we already have our selected customers in selectedDayCustomers              
                
                //For every selected customer =>
                for(Customer cust : selectedDayCustomers){
                    
                    
                    //Check if the customer can enter the store... (observer stuff)
                    
                        //Check what kind of cust is it...
                        //Check the amount of tools they already rented
                    if(rentalStore.canCustomerEnterStore(cust)){
                        //System.out.println("With customer " + cust.getName());
                        
                        //If yes, then we ask customer how many tools the want to rent (method in their classes)
                        //Depending on the customer, we determine how many tools to rent (randomly)
                        int rentDuration = cust.rentDuration();
                        int numOfTools = cust.howManyToolsToRent();
                        int numOfOptions = cust.howManyOptionsToRent(); 

                        //System.out.println("RentDuration = " + rentDuration);
                        //System.out.println("Number of tools rented = " + numOfTools);
                        //System.out.println("Number of options rented = " + numOfOptions);
                        
                                               
                        //Check if the customer already has a active rental, and hence an existing record
                        if(cust.hasActiveRental == false){                            
                        
                            //If not... then create one for them 
                            Record newRecord = new Record();
                            
                            //Set the reference to Customer our new record
                            newRecord.setCustomer(cust);
                            //Set the reference to Record for our customer
                            cust.setActiveRecord(newRecord);
        
                            newRecord.setNightsUntilDue(rentDuration);
                            rentalStore.addRentalRecord(newRecord);
                        }
                        //Get the customer's record...
                        Record currentCustomersRecord = cust.getCustomerRecord();

                        //System.out.println("Customer is renting " + cust.howManyToolsRented() + " tools.");

                        //Account for situations where customer alreay rented some tools (max is 3...)
                        if(cust.howManyToolsRented() < 3){
                            howManyMoreTools(cust.howManyToolsRented());
                        }else{
                            numOfTools = 0;
                        }
                        //System.out.println("Customer can only rent another " + numOfTools + " tools");
                        //Determine what options the customer wants...
                        cust.pickOptionsToRent(numOfOptions);
                        
                        //Add the selected tools to the record
                        ArrayList<Tool> pickedTools = rentalStore.selectedNTools(numOfTools);
                        currentCustomersRecord.addRentedTools(pickedTools);

                        rentalStore.updateDayRevenue(currentCustomersRecord.getTotalCost());
                        rentalStore.updateTotalRevenue();
                        
                        //Update Customer's hasActiveRental status (bool)
                        cust.hasActiveRental = true;
                      
                                               
                    }else{
                        //Customer can't enter store - get another customer...
                        continue;
                    }        
                }               
            }
            //Go through all records and decrement all daysUntilDue 
            decrementAllRecords();

            //Print today's total revenue 
            System.out.println("Total revenue for today: " + rentalStore.getFinalDayRevenue());

        }
        //At the end of the simulation we print the final report
        printEndSimResults();  

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
        sb.append("======================================================================\n");
        sb.append("                     Simulation Day# " + (dayNum+1) +                    "\n");
        sb.append("======================================================================\n");

        sb.append("\n");
        sb.append("Number of completed rentals today: " + todaysReturns.size());
        sb.append("\n");
        
        //Print all of today's completed rentals and details
        
        int i = 1;
        //Loop through all of the records in todaysReturns
        for(Record rec : todaysReturns){
            sb.append("======================================================\n");
            sb.append("             Todays Return #" + i + "\n");
            sb.append("======================================================\n");
        
            sb.append("Customer name: " + rec.getCustomer().getName() + "\n");
            sb.append("Return duration: " + rec.getRentDuration() + "\n");
            sb.append("Rental Total cost: " + rec.getTotalCostFormatCurr() + "\n");

            sb.append("==========================================\n");
            sb.append("             Rented Tools:       \n");
            sb.append("==========================================\n");

            //Loop through all the tools that were rented
            for(Tool rentedTool : rec.getRentedTools()){
                sb.append(rentedTool.getName() + "\n");
            }

            sb.append("==========================================\n");
            sb.append("             Rented Options:  \n");
            sb.append("==========================================\n");

            //Loop through all rented options in this record
            for(StoreOption op : rec.getRentedOptions()){
                //Print how many of that option and then the Description...
                if(op.getCount() > 0){
                    sb.append(op.getCount() + " " + op.getDescription() + "\n");
                }
            }
            sb.append("\n");
            i++;
        }          
        
        sb.append("==============================================\n");
        sb.append("              Active Rentals        \n");
        sb.append("==============================================\n");
        //Print all active rentals from the store...
        //Loop through all the records in currentRentalRecords...
        if(!rentalStore.getCurrentRentalRecords().isEmpty()){
            for(Record rec : rentalStore.getCurrentRentalRecords()){
                sb.append("Customer name: " + rec.getCustomer().getName() + "\n");
                //Loop through all the tools in the record and print the name of each tool
                for(Tool tool : rec.getRentedTools()){
                    //Intendeted to group by customer
                    sb.append("     " + tool.getName() + "\n");
                }
            }
        }else{
            sb.append("None \n");
        }
             
        sb.append("\n");
        sb.append("==============================================\n");
        sb.append("             Tools In store          \n");
        sb.append("==============================================\n");
        
        sb.append("Available tools in store: " + Integer.toString(rentalStore.howManyAvailToolsToRent()) + "\n");
        
        //Loop through the inventory of tools in store and print all the tools that have rentedOut = false
        for(Tool tool : rentalStore.getInventory()){
            if(tool.isRented() == false){
                //Print the name of the tool if isRented is false
                sb.append(tool.getName() + "\n");
            }
        }
        
        //Print the total amount of money that the store has made today   
        //Prints the whole stringBuffer
        System.out.println(sb);
    }
    /*
    ============================================================================
                        printEndSimResults()
        1. Print the total # of completed rentals
        2. Print # of rental by customer types
        3. Print total $ that the store has made for 35 day period
    ============================================================================
    */
    //This prints at the end of the simulation
    private void printEndSimResults(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("======================================================================\n");
        sb.append("                        End of Simulation Results                 \n");
        sb.append("======================================================================\n");

        //Print total number of completed records
        sb.append("Number of completed rentals: " + rentalStore.getPastRentalRecord().size() + "\n");

        //Print total number of completed rentals per customer category
        int numRegular = 0;
        int numCasual = 0;
        int numBusiness = 0;

        //Count the # of each customer type in past records
        numBusiness = howManyCustomerTypeRecords("business", rentalStore.getCurrentRentalRecords());
        numRegular = howManyCustomerTypeRecords("regular", rentalStore.getCurrentRentalRecords());
        numCasual = howManyCustomerTypeRecords("casual", rentalStore.getCurrentRentalRecords());

        sb.append("Number of Casual customers that rented: " + numCasual + "\n");
        sb.append("Number of Regular customers that rented: " + numRegular + "\n");
        sb.append("Number of Business customers that rented: " + numBusiness + "\n");
        sb.append("Total amount of money the store has made for " + (simulationNights+1) + " days: " + rentalStore.getFinalTotalRevenue() + "\n");

        //Print the total amount of money the store as made

        sb.append("======================================================================\n");

        System.out.println(sb.toString());
    }

    //Gets the count of passed in customer type in the ArrayList
    private int howManyCustomerTypeRecords(String type, ArrayList<Record> pastRecords){
        int count = 0;
        for(Record rec : pastRecords){
            if(rec.getCustomer().getCustType() == type){
                count++;
            }
        }
        return count;
    }

    void printSelectedCustomers( ArrayList<Customer> selected){
        for(Customer cust : selected){
            System.out.println(cust.getName());
        }
    }

    private int howManyMoreTools(int currentNumOfTools){
        int rand = 0;
        int max = 3 - currentNumOfTools;
        rand = genRandomNum(1,max);
        return rand;
    }
}