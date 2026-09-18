package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView textViewRecipeTitle = findViewById(R.id.textViewRecipeTitle);
        TextView textViewIngredients = findViewById(R.id.textViewIngredients);
        TextView textViewInstructions = findViewById(R.id.textViewInstructions);

        int recipeId = getIntent().getIntExtra("recipeId", -1);
        String recipeName = getIntent().getStringExtra("recipeName");
        String instructions = getIntent().getStringExtra("instructions");

        textViewRecipeTitle.setText(recipeName);
        textViewInstructions.setText(instructions);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<RecipeIngredient> ingredients = databaseHelper.getRecipeIngredients(recipeId);

        StringBuilder ingredientStringBuilder = new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {
            ingredientStringBuilder
                    .append("• ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append(" ")
                    .append(ingredient.getIngredientName())
                    .append("\n");
        }

        textViewIngredients.setText(ingredientStringBuilder.toString());
    }
}