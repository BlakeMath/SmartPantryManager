package com.example.smartpantrymanager;

public class RecipeIngredient {
    private int id;
    private int recipeId;
    private int ingredientName;
    private double quantity;
    private String unit;

    public RecipeIngredient(int id, int recipeId, int ingredientName, double quantity, String unit){
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getUnit() {
        return unit;
    }
    public double getQuantity() {
        return quantity;
    }
    public int getIngredientName() {
        return ingredientName;
    }
    public int getRecipeId() {
        return recipeId;
    }
}
