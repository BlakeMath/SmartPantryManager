package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 4;

    private static final String TABLE_PANTRY = "pantry_items";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_EXPIRY_DATE = "expiry_date";

    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPantryTable = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " REAL NOT NULL, " +
                COLUMN_UNIT + " TEXT NOT NULL, " +
                COLUMN_EXPIRY_DATE + " TEXT)";
        db.execSQL(createPantryTable);

        String createRecipeTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "instructions TEXT NOT NULL" +
                ")";
        db.execSQL(createRecipeTable);

        String createRecipeIngredientsTable = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipe_id INTEGER NOT NULL, " +
                "ingredient_name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT NOT NULL" +
                ")";
        db.execSQL(createRecipeIngredientsTable);

        seedRecipes(db);
    }

    //upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
    }

    //insert Recipe
    private long insertRecipe(SQLiteDatabase db, String name, String instructions) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("instructions", instructions);
        return db.insert("recipes", null, values);
    }

    //insert Recipe Ingredient
    private void insertRecipeIngredient(SQLiteDatabase db, long recipeId, String ingredientName, double quantity, String unit) {
        ContentValues values = new ContentValues();
        values.put("recipe_id", recipeId);
        values.put("ingredient_name", ingredientName);
        values.put("quantity", quantity);
        values.put("unit", unit);
        db.insert(TABLE_RECIPE_INGREDIENTS, null, values);
    }

    //generate recipes
    private void seedRecipes(SQLiteDatabase db) {
        long recipeId;

        // 1. Scrambled Eggs
        recipeId = insertRecipe(db,
                "Scrambled Eggs",
                "Beat the eggs. Melt butter in a pan. Add eggs and stir until cooked.");

        insertRecipeIngredient(db, recipeId, "egg", 2, "item");
        insertRecipeIngredient(db, recipeId, "butter", 10, "g");


        // 2. Cheese Omelette
        recipeId = insertRecipe(db,
                "Cheese Omelette",
                "Beat eggs. Cook in a buttered pan. Add cheese and fold.");

        insertRecipeIngredient(db, recipeId, "egg", 2, "item");
        insertRecipeIngredient(db, recipeId, "cheese", 50, "g");
        insertRecipeIngredient(db, recipeId, "butter", 10, "g");


        // 3. Tomato Toast
        recipeId = insertRecipe(db,
                "Tomato Toast",
                "Toast the bread. Slice tomato and place it on the toast.");

        insertRecipeIngredient(db, recipeId, "bread", 2, "slice");
        insertRecipeIngredient(db, recipeId, "tomato", 1, "item");


        // 4. Grilled Cheese
        recipeId = insertRecipe(db,
                "Grilled Cheese",
                "Butter the bread, add cheese and grill until golden.");

        insertRecipeIngredient(db, recipeId, "bread", 2, "slice");
        insertRecipeIngredient(db, recipeId, "cheese", 50, "g");
        insertRecipeIngredient(db, recipeId, "butter", 10, "g");


        // 5. Tuna Sandwich
        recipeId = insertRecipe(db,
                "Tuna Sandwich",
                "Mix tuna with mayonnaise and place between bread slices.");

        insertRecipeIngredient(db, recipeId, "bread", 2, "slice");
        insertRecipeIngredient(db, recipeId, "tuna", 100, "g");
        insertRecipeIngredient(db, recipeId, "mayonnaise", 20, "g");


        // 6. Chicken and Rice
        recipeId = insertRecipe(db,
                "Chicken and Rice",
                "Cook the rice. Cook the chicken thoroughly and serve together.");

        insertRecipeIngredient(db, recipeId, "chicken", 200, "g");
        insertRecipeIngredient(db, recipeId, "rice", 100, "g");


        // 7. Tomatoe Pasta
        recipeId = insertRecipe(db,
                "Tomatoe Pasta",
                "Cook pasta. Chop and cook tomatoes, then combine.");

        insertRecipeIngredient(db, recipeId, "pasta", 100, "g");
        insertRecipeIngredient(db, recipeId, "tomatoe", 2, "item");


        // 8. Cheese Pasta
        recipeId = insertRecipe(db,
                "Cheese Pasta",
                "Cook pasta. Add milk and cheese and stir until creamy.");

        insertRecipeIngredient(db, recipeId, "pasta", 100, "g");
        insertRecipeIngredient(db, recipeId, "cheese", 50, "g");
        insertRecipeIngredient(db, recipeId, "milk", 100, "ml");


        // 9. Banana Oats
        recipeId = insertRecipe(db,
                "Banana Oats",
                "Cook oats with milk. Slice banana and add on top.");

        insertRecipeIngredient(db, recipeId, "oats", 50, "g");
        insertRecipeIngredient(db, recipeId, "banana", 1, "item");
        insertRecipeIngredient(db, recipeId, "milk", 200, "ml");


        // 10. Peanut Butter Toast
        recipeId = insertRecipe(db,
                "Peanut Butter Toast",
                "Toast the bread and spread peanut butter over it.");

        insertRecipeIngredient(db, recipeId, "bread", 2, "slice");
        insertRecipeIngredient(db, recipeId, "peanut butter", 30, "g");


        // 11. Egg Fried Rice
        recipeId = insertRecipe(db,
                "Egg Fried Rice",
                "Cook the egg in oil, add cooked rice and fry together.");

        insertRecipeIngredient(db, recipeId, "rice", 150, "g");
        insertRecipeIngredient(db, recipeId, "egg", 1, "item");
        insertRecipeIngredient(db, recipeId, "oil", 10, "ml");


        // 12. Chicken Sandwich
        recipeId = insertRecipe(db,
                "Chicken Sandwich",
                "Cook chicken, slice it and place between bread with mayonnaise.");

        insertRecipeIngredient(db, recipeId, "bread", 2, "slice");
        insertRecipeIngredient(db, recipeId, "chicken", 150, "g");
        insertRecipeIngredient(db, recipeId, "mayonnaise", 20, "g");


        // 13. Banana Yogurt Bowl
        recipeId = insertRecipe(db,
                "Banana Yogurt Bowl",
                "Slice banana and mix with yogurt.");

        insertRecipeIngredient(db, recipeId, "banana", 1, "item");
        insertRecipeIngredient(db, recipeId, "yogurt", 200, "g");


        // 14. Apple Oats
        recipeId = insertRecipe(db,
                "Apple Oats",
                "Cook oats with milk and add chopped apple.");

        insertRecipeIngredient(db, recipeId, "oats", 50, "g");
        insertRecipeIngredient(db, recipeId, "apple", 1, "item");
        insertRecipeIngredient(db, recipeId, "milk", 200, "ml");


        // 15. Tomatoe Egg Scramble
        recipeId = insertRecipe(db,
                "Tomatoe Egg Scramble",
                "Cook chopped tomatoe in butter. Add beaten eggs and scramble.");

        insertRecipeIngredient(db, recipeId, "egg", 2, "item");
        insertRecipeIngredient(db, recipeId, "tomatoe", 1, "item");
        insertRecipeIngredient(db, recipeId, "butter", 10, "g");
    }

    //add Pantry Item
    public boolean addPantryItem(PantryItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        long result = db.insert(TABLE_PANTRY, null, values);
        return result != -1; //if result is -1, insert failed otherwise it succeeded
    }

    //update Pantry Item
    public boolean updatePantryItem(PantryItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        int rowsAffected = db.update(
                TABLE_PANTRY,
                values,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(item.getId())}
        );

        return rowsAffected > 0;
    }

    //delete Pantry Item
    public boolean deletePantryItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_PANTRY,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        return rowsDeleted > 0;
    }

    //get all pantry items
    public ArrayList<PantryItem> getAllPantryItems(){
        ArrayList<PantryItem> pantryItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PANTRY;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do { //loops through all rows
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT));
                String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPIRY_DATE));

                PantryItem item = new PantryItem(id, name, quantity, unit, expiryDate);

                pantryItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pantryItems;
    }

    //get all recipes
    public ArrayList<Recipe> getAllrecipes(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);;

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String instructions = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));

                Recipe recipe = new Recipe(id, name, instructions);
                recipes.add(recipe);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return recipes;
    }

    //get recipe ingredients
    public ArrayList<RecipeIngredient> getRecipeIngredients(int recipeId) {
        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_RECIPE_INGREDIENTS +
                        " WHERE recipe_id = ?",
                new String[]{String.valueOf(recipeId)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String ingredientName = cursor.getString(cursor.getColumnIndexOrThrow("ingredient_name"));
                double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));

                recipeIngredients.add(
                        new RecipeIngredient(
                                id,
                                recipeId,
                                ingredientName,
                                quantity,
                                unit
                        )
                );
            } while(cursor.moveToNext());
        }
        cursor.close();
        return recipeIngredients;
    }

    //normalize ingredient name
    private String normalizeIngredientName(String name){
        String normalized = name.toLowerCase().trim();

        if(normalized.equals("tomato") || normalized.equals("tomotoe")){
            return "tomatoes";
        }

        if (normalized.endsWith("s")){
            normalized = normalized.substring(0,normalized.length()-1);
        }

        return normalized;
    }

    // get base unit
    private String getBaseUnit(String unit){
        String normalized = unit.toLowerCase().trim();

        switch(normalized) {
            case "kg":
            case "g":
            case "gram":
            case "grams":
                return "g";

            case "l":
            case "litre":
            case "litres":
            case "liter":
            case "liters":
            case "ml":
                return "ml";

            case "item":
            case "items":
            case "each":
                return "item";

            case "slice":
            case "slices":
                return "slices";

            default:
                return normalized;
        }
    }

    //convert quantity to base unit
    private double convertToBaseQuantity(double quantity, String unit){
        String normalized = unit.toLowerCase().trim();

        switch(normalized){
            case "kg":
                return quantity * 1000;

            case "l":
            case "litre":
            case "litres":
            case "liter":
            case "liters":
                return quantity*1000;
            default:
                return quantity;
        }
    }

    //check if recipe has enough ingredient
    private boolean hasEnoughIngredient(RecipeIngredient requiredIngredient, ArrayList<PantryItem> pantryItems){
        String requiredName = normalizeIngredientName(requiredIngredient.getIngredientName());
        String requiredUnit = getBaseUnit(requiredIngredient.getUnit());
        double requiredQuantity = convertToBaseQuantity(requiredIngredient.getQuantity(), requiredIngredient.getUnit());

        double availableQuantity = 0;

        for (PantryItem pantryItem : pantryItems){
            String pantryName = normalizeIngredientName(pantryItem.getName());
            String pantryUnit = getBaseUnit(pantryItem.getUnit());

            if(requiredName.equals(pantryName) && requiredUnit.equals(pantryUnit)){
                availableQuantity += convertToBaseQuantity(pantryItem.getQuantity(), pantryItem.getUnit());
            }
        }
        return availableQuantity >= requiredQuantity;
    }

    //Strict Recipe Suggestion
    public ArrayList<Recipe> getSuggestedRecipes(){
        ArrayList<Recipe> suggestedRecipes = new ArrayList<>();
        ArrayList<Recipe> allRecipes = getAllrecipes();
        ArrayList<PantryItem> pantryItems = getAllPantryItems();

        //loop through all recipes
        for(Recipe recipe : allRecipes){
            ArrayList<RecipeIngredient> requiredIngredients = getRecipeIngredients(recipe.getId());
            boolean canMakeRecipe = true;
            //check if recipe has enough ingredients
            for(RecipeIngredient required : requiredIngredients){
                //if recipe does not have enough ingredients, set canMakeRecipe to false and break out of loop
                if(!hasEnoughIngredient(required, pantryItems)){
                    canMakeRecipe = false;
                    break;
                }
            }

            //if recipe has enough ingredients, add to suggested recipes
            if(canMakeRecipe){
                suggestedRecipes.add(recipe);
            }
        }
        return suggestedRecipes;
    }
}
