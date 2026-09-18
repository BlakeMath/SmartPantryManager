package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    //create the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView textViewRecipeTitle = findViewById(R.id.textViewRecipeTitle);
        TextView textViewIngredients = findViewById(R.id.textViewIngredients);
        TextView textViewInstructions = findViewById(R.id.textViewInstructions);
        Button buttonNavPantry = findViewById(R.id.buttonNavPantry);
        Button buttonNavRecipes = findViewById(R.id.buttonNavRecipes);
        Button buttonNavSettings = findViewById(R.id.buttonNavSettings);

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

        //button listeners
        buttonNavRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetailActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
            finish();
        });

        buttonNavSettings.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetailActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });

        buttonNavPantry.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}