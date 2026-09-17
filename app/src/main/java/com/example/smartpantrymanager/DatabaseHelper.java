package com.example.smartpantrymanager;

//imports
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 3;

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

    }

    //upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
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
}
