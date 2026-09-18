package com.example.smartpantrymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchExpiryAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchExpiryAlerts = findViewById(R.id.switchExpiryAlerts);
        Button buttonNavPantry = findViewById(R.id.buttonNavPantry);
        Button buttonNavRecipes = findViewById(R.id.buttonNavRecipes);
        Button buttonNavSettings = findViewById(R.id.buttonNavSettings);

        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        boolean expiryAlertsEnabled = preferences.getBoolean("expiry_alerts_enabled", false);
        switchExpiryAlerts.setChecked(expiryAlertsEnabled);

        switchExpiryAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("expiry_alerts_enabled", isChecked);
            editor.apply();
        });

        //button listeners
        buttonNavRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
            finish();
        });

        buttonNavSettings.setOnClickListener(v -> {
            //nothing because we are already on the screen
        });

        buttonNavPantry.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}