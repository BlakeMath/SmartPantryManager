package com.example.smartpantrymanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerViewPantry;
    private PantryAdapter pantryAdapter;
    private DatabaseHelper databaseHelper;
    private ArrayList<PantryItem> pantryItems;

    //create the activity
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views
        recyclerViewPantry = findViewById(R.id.recyclerViewPantry);
        Button buttonAddIngredient = findViewById(R.id.buttonAddIngredient);
        Button buttonSuggestedRecipes = findViewById(R.id.buttonSuggestedRecipes);
        Button buttonNavPantry = findViewById(R.id.buttonNavPantry);
        Button buttonNavRecipes = findViewById(R.id.buttonNavRecipes);
        Button buttonNavSettings = findViewById(R.id.buttonNavSettings);

        databaseHelper = new DatabaseHelper(this);

        pantryItems = databaseHelper.getAllPantryItems();

        //create adapter
        pantryAdapter = new PantryAdapter(
                pantryItems,
                new PantryAdapter.OnItemClickListener() {

                    @Override
                    public void onEditClick(PantryItem item) {

                        Intent intent = new Intent(
                                MainActivity.this,
                                AddEditIngredientActivity.class
                        );

                        intent.putExtra("id", item.getId());
                        intent.putExtra("name", item.getName());
                        intent.putExtra("quantity", item.getQuantity());
                        intent.putExtra("unit", item.getUnit());
                        intent.putExtra("expiryDate", item.getExpiryDate());

                        startActivity(intent);
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDeleteClick(PantryItem item) {

                        boolean success =
                                databaseHelper.deletePantryItem(item.getId());

                        if (success) {
                            pantryItems.remove(item);
                            pantryAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );

        recyclerViewPantry.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPantry.setAdapter(pantryAdapter);

        //button listeners
        buttonAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        buttonSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });

        buttonNavRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });

        buttonNavSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        buttonNavPantry.setOnClickListener(v -> {
            //nothing because we are already on the screen
        });
    }

    //update the activity
    @Override
    protected void onResume(){
        super.onResume();

        if(databaseHelper != null && pantryAdapter != null){
            pantryItems.clear();
            pantryItems.addAll(databaseHelper.getAllPantryItems());
            pantryAdapter.notifyDataSetChanged();
        }
    }
}
