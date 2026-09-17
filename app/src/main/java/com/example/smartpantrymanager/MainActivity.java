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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPantry = findViewById(R.id.recyclerViewPantry);
        Button buttonAddIngredient = findViewById(R.id.buttonAddIngredient);

        databaseHelper = new DatabaseHelper(this);

        pantryItems = databaseHelper.getAllPantryItems();

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

        buttonAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        Button buttonSuggestedRecipes = findViewById(R.id.buttonSuggestedRecipes);
        buttonSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });
    }

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
