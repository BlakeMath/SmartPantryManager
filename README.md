# SMART PANTRY MANAGER
The Smart Pantry Manager helps a user reduce food waste by tracking the ingredients they actually have at home (their "pantry")
and suggesting recipes they can cook using strictly those leftover ingredients - no shopping trip required,
and no recipe suggested unless the user genuinely already has everything it needs.
This app is for users that do not want to waste their food and want suggestions on what recipes they can make based on what they have.

## Features
- Add Ingredient
- Edit Ingredient
- Delete Ingredient
- Store Ingredient Details
- Expiry Alerts
- View Suggested Recipes
- View Recipe Ingredients and Cooking Instructions
- Settings for Alerts
  
## Database
The Smart Pantry Manager uses a SQLite database for local storage. It was chosen as it is built into
Android and it allows us to locally store data without having internet connection or a database server.

The Smart Pantry Manager has 3 database tables:
1. 'pantry_items' - Stores the users pantry ingredients.
2. 'recipes' - Stores the pre-defined recipes.
3. 'recipe_ingredients' - Stores the ingredients required for each recipe.

## Technology Used
- Java
- Android Studio
- SQLite

## How to Run the Application
1. Download this repository
2. Open the project in Android Studio
3. Start the Android emulator or alternatively connect and android device
4. Run the application on Android Studio
5. The Smart Pantry Manager will then open and can be interacted with.

## Author
- Blake Mathieson
- BSc in IT (3rd Year 2026)
- Module: Mobile App Development 700
- Student Number: 402410586
