package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity{
    private DatabaseHelper databaseHelper;
    private RecipeAdapter recipeAdapter;
    private ArrayList<Recipe> suggestedRecipes;

    //create the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        RecyclerView recyclerViewRecipes = findViewById(R.id.recyclerViewRecipes);
        TextView textViewNoRecipes = findViewById(R.id.textViewNoRecipes);
        Button buttonNavPantry = findViewById(R.id.buttonNavPantry);
        Button buttonNavRecipes = findViewById(R.id.buttonNavRecipes);
        Button buttonNavSettings = findViewById(R.id.buttonNavSettings);

        databaseHelper = new DatabaseHelper(this);
        suggestedRecipes = databaseHelper.getSuggestedRecipes();
        recipeAdapter = new RecipeAdapter(
                suggestedRecipes,
                recipe -> {

                    Intent intent = new Intent(
                            SuggestedRecipesActivity.this,
                            RecipeDetailActivity.class
                    );

                    intent.putExtra("recipeId", recipe.getId());
                    intent.putExtra("recipeName", recipe.getName());
                    intent.putExtra("instructions", recipe.getInstructions());

                    startActivity(intent);
                }
        );

        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewRecipes.setAdapter(recipeAdapter);

        if (suggestedRecipes.isEmpty()) {
            textViewNoRecipes.setVisibility(View.VISIBLE);
            recyclerViewRecipes.setVisibility(View.GONE);
        } else {
            textViewNoRecipes.setVisibility(View.GONE);
            recyclerViewRecipes.setVisibility(View.VISIBLE);
        }

        //button listeners
        buttonNavRecipes.setOnClickListener(v -> {
            //nothing because we are already on the screen
        });

        buttonNavSettings.setOnClickListener(v -> {
            Intent intent = new Intent(SuggestedRecipesActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        });

        buttonNavPantry.setOnClickListener(v -> {
            Intent intent = new Intent(SuggestedRecipesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}