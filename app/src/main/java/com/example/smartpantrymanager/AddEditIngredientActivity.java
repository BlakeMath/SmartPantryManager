package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity{
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextUnit;
    private EditText editTextExpiryDate;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        editTextName = findViewById(R.id.editTextName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextUnit = findViewById(R.id.editTextUnit);
        editTextExpiryDate = findViewById(R.id.editTextExpiryDate);

        Button buttonSave = findViewById(R.id.buttonSave);

        databaseHelper = new DatabaseHelper(this);

        buttonSave.setOnClickListener(v -> saveIngredient());
    }

    //save ingredient, checks if fields are fulled in, creates the object then uses that to add to the database using addPantryItem method
    private void saveIngredient(){
        //get values from fields
        String name = editTextName.getText().toString().trim();
        String quantityText = editTextQuantity.getText().toString().trim();
        String unit = editTextUnit.getText().toString().trim();
        String expiryDate = editTextExpiryDate.getText().toString().trim();

        //check if fields are empty
        if(name.isEmpty() || quantityText.isEmpty() || unit.isEmpty() || expiryDate.isEmpty()){
            Toast.makeText(this, "Please Complete All Required Fields", Toast.LENGTH_SHORT).show();
            return;
        }

        //convert quantity to double
        double quantity = Double.parseDouble(quantityText);

        //create new PantryItem object
        PantryItem item = new PantryItem(
                0,
               name,
               quantity,
               unit,
               expiryDate
        );

        //add item to database
        boolean success = databaseHelper.addPantryItem(item);

        //check if item was added successfully
        if(success){
            Toast.makeText(this, "Ingredient Added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Failed to Add Ingredient", Toast.LENGTH_SHORT).show();
        }
    }
}