package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity{
    private DatabaseHelper databaseHelper;
    private RecipeAdapter recipeAdapter;
    private ArrayList<Recipe> suggestedRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        RecyclerView recyclerViewRecipes = findViewById(R.id.recyclerViewRecipes);
        TextView textViewNoRecipes = findViewById(R.id.textViewNoRecipes);

        databaseHelper = new DatabaseHelper(this);
        suggestedRecipes = databaseHelper.getSuggestedRecipes();
        recipeAdapter = new RecipeAdapter(suggestedRecipes);

        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewRecipes.setAdapter(recipeAdapter);

        if(suggestedRecipes.isEmpty()){
            textViewNoRecipes.setVisibility(View.VISIBLE);
        }else{
            textViewNoRecipes.setVisibility(View.GONE);
        }

    }

}