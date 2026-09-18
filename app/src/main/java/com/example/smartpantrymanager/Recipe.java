package com.example.smartpantrymanager;

public class Recipe {
    private int id;
    private String name;
    private String instructions;

    // Recipe Constructor
    public Recipe(int id, String name, String instructions) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInstructions() {
        return instructions;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
