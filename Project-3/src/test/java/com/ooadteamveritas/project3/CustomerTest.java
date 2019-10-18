package com.ooadteamveritas.project3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerTest {
    public CustomerTest() {
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
    
    @Test
    public void testPickRent() {
        System.out.println("Testing random option picking");
        Customer test = new CasualCustomer("Test");
        int numOptions = test.howManyOptionsToRent();
        
        //Testing customer got a valid number of options
        assertTrue(numOptions >= 1 && numOptions <= 6);
    }
}
