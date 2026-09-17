package com.example.smartpantrymanager;

public class RecipeIngredient {
    private int id;
    private int recipeId;
    private String ingredientName;
    private double quantity;
    private String unit;

    public RecipeIngredient(int id, int recipeId, String ingredientName, double quantity, String unit){
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
    public String getIngredientName() {
        return ingredientName;
    }
    public int getRecipeId() {
        return recipeId;
    }
}
