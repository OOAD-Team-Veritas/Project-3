package com.ooadteamveritas.project3;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class MyUnitTest {
    public MyUnitTest(){
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Customer Tests
     */
    
    // Test that customer will choose options to add to rental
    @Test
    public void testHowManyOptions() {
        System.out.println("Testing that customer can choose options...");
        Customer test = new CasualCustomer("Test");
        int numOptions = test.howManyOptionsToRent();
        
        //Testing customer got a valid number of options
        assertTrue(numOptions >= 0 && numOptions <= 6);
    }
    
    // Test that a record can be added for the customer
    @Test
    public void testSetRecord() {
        System.out.println("Testing that record can be added for customer...");
        Customer testCust = new CasualCustomer("Test");
        Record testRecord = new Record();
        testCust.setActiveRecord(testRecord);
        
        assertTrue(testCust.record instanceof Record);
    }
    
    /***
     * Record Tests
     */
    
    // Test that recorded tools can be accessed
    @Test
    public void testGetRentedTools() {
        System.out.println("Testing that rented tools can be accessed...");
        Record testRecord = new Record();
        assertTrue(testRecord.getRentedTools() instanceof ArrayList);
    }
    
    /***
     * SimpleToolFactory Tests
     */
    //Testing if we are getting correct instances...
    @Test
    public void testCreateToolCase1() {
        System.out.println("Testing creating A PaintTool...");
        String type = "paint";
        String name = "Paint Tool 1";
        SimpleToolFactory instance = new SimpleToolFactory();
        Tool result = instance.createTool(type, name); 
        
        //Testing if result is a PaintTool
        assertTrue(result instanceof PaintTool);
    }
    
    //Testing if we are getting correct instances...
    @Test
    public void testCreateToolCase2() {
        System.out.println("Testing creating A ConcreteTool...");
        String type = "concrete";
        String name = "Concrete Tool 1";
        SimpleToolFactory instance = new SimpleToolFactory();
        Tool result = instance.createTool(type, name); 
        
        //Testing if result is a ConcreteTool
        assertTrue(result instanceof ConcreteTool);
    }
    
    //Testing if we are getting correct instances...
    @Test
    public void testCreateToolCase3() {
        System.out.println("Testing creating A PlumbingTool...");
        String type = "plumbing";
        String name = "Plumbing Tool 1";
        SimpleToolFactory instance = new SimpleToolFactory();
        Tool result = instance.createTool(type, name); 
        
        //Testing if result is a PlumbingTool
        assertTrue(result instanceof PlumbingTool);
    }
    
    //Testing if we are getting correct instances...
    @Test
    public void testCreateToolCase4() {
        System.out.println("Testing creating A WoodworkTool...");
        String type = "woodwork";
        String name = "Woodwork Tool 1";
        SimpleToolFactory instance = new SimpleToolFactory();
        Tool result = instance.createTool(type, name); 
        
        //Testing if result is a WoodworkTool
        assertTrue(result instanceof WoodworkTool);
    }
    
    //Testing if we are getting correct instances...
    @Test
    public void testCreateToolCase5() {
        System.out.println("Testing creating A YardworkTool...");
        String type = "yardwork";
        String name = "Yardwork Tool 1";
        SimpleToolFactory instance = new SimpleToolFactory();
        Tool result = instance.createTool(type, name); 
        
        //Testing if result is a WoodworkTool
        assertTrue(result instanceof YardworkTool);
    }
    
    /***
     * Tool Tests
     */
    // test that the tool can be rented
    @Test
    public void testRentTool() {
        System.out.println("Testing that tool can be rented...");
        Tool testTool = new WoodworkTool("Test");
        testTool.rent();
        assertTrue(testTool.isRented());
    }
    
    /***
     * Store Simulation Tests
     */
    @Test
    public void testcheckIfAlreadySelectedCase1() {
        ArrayList<Customer> CustomerList = new ArrayList<Customer>(); 
        Customer cust1 = new RegularCustomer("Regular 1");
        Customer cust2 = new RegularCustomer("Regular 2");
        
        CustomerList.add(cust1);
        
        storeSimulation instance = new storeSimulation(34,4);
        boolean test = instance.checkIfAlreadySelectedCust(cust2, CustomerList); 
        System.out.println(test);
        assertFalse(test);
    }
    
    @Test
    public void testcheckIfAlreadySelectedCase2() {
        ArrayList<Customer> CustomerList = new ArrayList<Customer>(); 
        Customer cust1 = new RegularCustomer("Regular 1");
        
        CustomerList.add(cust1);
        
        storeSimulation instance = new storeSimulation(34,4);
        boolean test = instance.checkIfAlreadySelectedCust(cust1, CustomerList); 
        System.out.println(test);
        assertTrue(test);
    }
}
