package com.ooadteamveritas.project3;

public abstract class Customer {
    protected String name;
    protected Tool[] tools;
    protected Record record;
    
    public String getName() {
        return this.name;
    }
    
    public Tool[] getTools() {
        return this.tools;
    }
    
    public abstract void rent();
}
