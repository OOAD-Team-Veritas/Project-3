package com.ooadteamveritas.project3;

//The basic simple factory pattern
public class SimpleToolFactory {
    
    public Tool createTool(String type, String name){
        Tool newTool = null;
        
        if(type.equals("paint")){
            newTool = new PaintTool(name);
        }else if(type.equals("concrete")){
            newTool = new ConcreteTool(name);
        }else if(type.equals("plumbing")){
            newTool = new PlumbingTool(name);
        }else if(type.equals("woodwork")){
            newTool = new WoodworkTool(name); 
        }else if(type.equals("yardwork")){
            newTool = new YardworkTool(name);
        }
        return newTool;
    }
}
   
