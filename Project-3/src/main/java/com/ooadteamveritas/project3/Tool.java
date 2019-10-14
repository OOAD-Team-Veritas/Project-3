package com.ooadteamveritas.project3;

//Product interface (Simple factory pattern)
public abstract class Tool {
    protected double price;
    protected String name;
    protected String category;
    
    public double getPrice(){
        return this.price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getCategory(){
        return this.category;
    }
    
    public String toString() {
        StringBuffer display = new StringBuffer();
        return display.toString();
    }
}
