package edu.psu.ist411.recipe;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author Single-eye
 */
public class RecipeDAO {

   private static final String DATA_FILE = "Recipe.json";
   private int lastId;
   private HashSet<Recipe> recipes;

   public RecipeDAO() throws IOException {
      Path data = Paths.get(DATA_FILE);
      recipes = new HashSet<>();
      
      if (!Files.exists(Paths.get(DATA_FILE))) {
         Files.createFile(Paths.get(DATA_FILE));
         saveAll();
      }

      jsonToObj();
   }

   /**
    * Returns the specified recipe
    *
    * @param id the ID of the recipe
    * @return the recipe or null if not found
    */
   public Recipe getRecipeById(int id) {
      for (Iterator<Recipe> iterator = recipes.iterator(); iterator.hasNext();) {
         Recipe next = iterator.next();
         if (next.getRecipeID() == id) {
            return next;
         }
      }

      return null;
   }

   /**
    * Creates and saves a new recipe
    *
    * @param name the recipe's name
    * @param ingredients the recipe's ingredients
    * @return a new Recipe
    */
   public Recipe addRecipe(String name, HashSet<String> ingredients) {

      if (name == null || ingredients == null) {
         throw new IllegalArgumentException("Name and ingredients are required");
      }

      Recipe recipe = null;

      lastId++;
      final int id = lastId;

      recipe = new Recipe(id, name, ingredients);
      saveRecipe(recipe);

      saveAll();

      return recipe;
   }

   /**
    * Saves the specified recipe. Existing recipes are updated.
    *
    * @param recipe the recipe to add
    */
   public void saveRecipe(Recipe recipe) {
      if (recipe == null) {
         throw new IllegalArgumentException("recipe is required");
      }

      boolean newRecipe = recipes.add(recipe);
      if (!newRecipe) {
         recipes.remove(recipe);
         recipes.add(recipe);
      }

      saveAll();
   }

   /**
    * Deletes the recipe with the specified ID if it exists
    *
    * @param id the ID of the recipe to delete
    */
   public void deleteRecipe(int id) {
      for (Iterator<Recipe> iterator = recipes.iterator(); iterator.hasNext();) {
         Recipe next = iterator.next();
         if (next.getRecipeID() == id) {
            iterator.remove();
            break;
         }
      }
      saveAll();
   }

   public JsonObject objToJSon(int inID, String inName, HashSet<String> ingredients) {
      /*
        {"recipeName":"lamb saag",
        "ingredients": [
                {"ingredientName":"SALT"},
                {"ingredientName":"PEPPER"}
            ]
        }
       */

      JsonObjectBuilder recipeBuilder = Json.createObjectBuilder();

      JsonObjectBuilder ingredientBuilder = Json.createObjectBuilder();
      JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
      //create Json objects for each Ingredient obj within arraylist
      for (String ingredient : ingredients) {
         ingredientBuilder.add("ingredientName", ingredient);
         /*
            {"ingredientName","SALT"}
          */
         arrayBuilder.add(ingredientBuilder);
      }
      JsonArray ingredientsArray = arrayBuilder.build();

      recipeBuilder.add("recipeID", inID);
      recipeBuilder.add("recipeName", inName);
      recipeBuilder.add("ingredients", ingredientsArray);
      JsonObject recipe = recipeBuilder.build();

      return recipe;

   }

   public void jsonToObj() {

      recipes.clear();

      try (InputStreamReader inputStream = new InputStreamReader(Files.newInputStream(Paths.get(DATA_FILE)));
              JsonReader jsonReader = Json.createReader(inputStream)) {

         JsonArray records = jsonReader.readArray();

         for (JsonValue record : records) {
            if (record.getValueType().equals(JsonValue.ValueType.OBJECT)) {
               JsonObject jsonRecipe = (JsonObject) record;

               int iD = jsonRecipe.getInt("recipeID");
               System.out.println(iD);
               String name = jsonRecipe.getString("recipeName");
               System.out.println(name);

               JsonArray ingredientArray;
               HashSet<String> ingredients = new HashSet();
               ingredientArray = jsonRecipe.getJsonArray("ingredients");
               for (JsonValue i : ingredientArray) {
                  JsonObject jsonObject = (JsonObject) i;
                  String holder = jsonObject.getString("ingredientName");
                  System.out.println("*****" + holder);
                  ingredients.add(holder);
               }

               Recipe JsonRecipe = new Recipe(iD, name, ingredients);
               recipes.add(JsonRecipe);
               
               if (iD > lastId) {
                  lastId = iD;
               }
            }
         }

      } catch (IOException ex) {
         Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   /**
    * Saves all recipes, overwriting any existing data
    */
   protected void saveAll() {

      JsonArrayBuilder outBuilder = Json.createArrayBuilder();
      JsonArray outArray;

      for (Recipe recipe : recipes) {
         JsonObject jo = objToJSon(recipe.getRecipeID(), recipe.getName(), recipe.getRecipeIngredients());
         if (jo != null) {
            outBuilder.add(jo);
         }
      }

      outArray = outBuilder.build();

      try (OutputStream out = Files.newOutputStream(Paths.get(DATA_FILE), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
              JsonWriter jsonWriter = Json.createWriter(out);) {
         jsonWriter.writeArray(outArray);
         out.flush();
      } catch (IOException ex) {
         System.err.println(ex.getMessage());
      }

   }

}
