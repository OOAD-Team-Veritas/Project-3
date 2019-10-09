/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ooadteamveritas.project3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riads
 */
public class SimpleToolFactoryTest {
    
    public SimpleToolFactoryTest() {
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
    
}
