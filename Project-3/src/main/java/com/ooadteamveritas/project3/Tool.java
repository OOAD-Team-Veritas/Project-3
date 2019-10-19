package com.ooadteamveritas.project3;

//Product interface (Simple factory pattern)
public abstract class Tool {
    protected double price;
    protected String name;
    protected String category;          //What category it belongs to...
    public boolean rentedOut;           //Is the tool curretly rented out?
    
    public Tool(){
        this.rentedOut = false;
    }
    
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
    
    public boolean isRented(){
        return this.rentedOut;
    }

    public void rent(){
        rentedOut = true;
    }
}