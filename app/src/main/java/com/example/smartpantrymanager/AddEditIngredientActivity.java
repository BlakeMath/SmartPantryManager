package com.example.smartpantrymanager;

import android.annotation.SuppressLint;
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
    private int itemId = -1;

    private DatabaseHelper databaseHelper;

    //create the activity
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

        if(getIntent().hasExtra("id")){
            itemId = getIntent().getIntExtra("id", -1);
            editTextName.setText(getIntent().getStringExtra("name"));
            editTextQuantity.setText(String.valueOf(getIntent().getDoubleExtra("quantity", 0)));
            editTextUnit.setText(getIntent().getStringExtra("unit"));
            editTextExpiryDate.setText(getIntent().getStringExtra("expiryDate"));
            buttonSave.setText("Update Ingredient");
        }

        buttonSave.setOnClickListener(v -> saveIngredient());
    }

    //save ingredient, checks if fields are fulled in, creates the object then uses that to add to the database using addPantryItem method
    private void saveIngredient(){
        //get values from fields
        String name = editTextName.getText().toString().trim();
        String quantityText = editTextQuantity.getText().toString().trim();
        String unit = editTextUnit.getText().toString().trim();
        String expiryDate = editTextExpiryDate.getText().toString().trim();

        //validate fields
        if(name.isEmpty()){
            editTextName.setError("Name is required");
            return;
        }
        if(quantityText.isEmpty()){
            editTextQuantity.setError("Quantity is required");
            return;
        }
        if (unit.isEmpty()){
            editTextUnit.setError("Unit is required");
            return;
        }

        //convert quantity to double
        double quantity = -1;
        try {
            quantity = Double.parseDouble(quantityText);
        }catch(NumberFormatException e){
            editTextQuantity.setError("Invalid quantity");
            return;
        }
        if (quantity <= 0){
            editTextQuantity.setError("Quantity must be greater than 0");
            return;
        }

        //validate expiry date if added
        if (!expiryDate.isEmpty() && !expiryDate.matches("\\d{4}-\\d{2}-\\d{2}")){
            editTextExpiryDate.setError("Use format YYYY-MM-DD");
            return;
        }

        //create new PantryItem object
        PantryItem item = new PantryItem(
                0,
               name,
               quantity,
               unit,
               expiryDate
        );

        //add item to database
        boolean success;
        if (itemId == -1){
            success = databaseHelper.addPantryItem(item);
        }else{
            item.setId(itemId);
            success = databaseHelper.updatePantryItem(item);
        }

        //check if item was added successfully
        if(success){
            Toast.makeText(this, itemId == -1 ? "Ingredient Saved" : "Ingredient Updated", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Unable to save ingredient", Toast.LENGTH_SHORT).show();
        }
    }
}