/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ooadteamveritas.project3;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riads
 */
public class storeSimulationTest {
    
    public storeSimulationTest() {
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
     * Test of runSimulation method, of class storeSimulation.
     */
    @Test
    public void testcheckIfAlreadySelectedCase1() {
        ArrayList<Customer> CustomerList = new ArrayList<Customer>(); 
        Customer cust1 = new RegularCustomer("Regular 1");
        Customer cust2 = new RegularCustomer("Regular 2");
        
        CustomerList.add(cust1);
        
        storeSimulation instance = new storeSimulation(34,4);
        boolean test = instance.checkIfAlreadySelected(cust2, CustomerList); 
        System.out.println(test);
        assertFalse(test);
    }
    
    @Test
    public void testcheckIfAlreadySelectedCase2() {
        ArrayList<Customer> CustomerList = new ArrayList<Customer>(); 
        Customer cust1 = new RegularCustomer("Regular 1");
        
        CustomerList.add(cust1);
        
        storeSimulation instance = new storeSimulation(34,4);
        boolean test = instance.checkIfAlreadySelected(cust1, CustomerList); 
        System.out.println(test);
        assertTrue(test);
    }
    
}
