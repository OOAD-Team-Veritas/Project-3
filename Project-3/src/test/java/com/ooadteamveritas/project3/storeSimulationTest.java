/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ooadteamveritas.project3;

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
    public void testRunSimulation() {
        boolean test = true;
        storeSimulation instance = new storeSimulation(5,5);
        double ranVal = instance.genRandomNum(1, 4);
        for(int i=0; i < 20; i++){
            ranVal = instance.genRandomNum(1, 4);
            if((ranVal >  4)||( ranVal < 1))
                test = false;     
        }
        assertTrue(test);
    }
    
}
