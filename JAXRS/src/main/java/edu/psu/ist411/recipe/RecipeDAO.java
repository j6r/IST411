package edu.psu.ist411.recipe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.util.HashSet;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author Single-eye
 */
public class RecipeDAO {

    public RecipeDAO() {
           
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

        try {
            OutputStream out = new FileOutputStream("Recipe.json");
            JsonWriter jsonWriter = Json.createWriter(out);
            jsonWriter.writeObject(recipe);
            System.out.println(recipe);
            out.close();
            jsonWriter.close();
            return recipe;
          
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return null;
    }

    public void jsonToObj() {

        try{
           InputStream inputStream = new FileInputStream("Recipe.json");

            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            JsonReader jsonReader = Json.createReader(reader);
            JsonObject jsonRecipe = jsonReader.readObject();
            
            
           int iD = jsonRecipe.getInt("recipeID");
           System.out.println(iD); 
           String name = jsonRecipe.getString("recipeName");
           System.out.println(name);
            
           JsonArray ingredientArray;
           HashSet<String> ingredients= new HashSet(); 
           ingredientArray = jsonRecipe.getJsonArray("ingredients");
           for(JsonValue i: ingredientArray){
               JsonObject jsonObject = (JsonObject) i;
               String holder = jsonObject.getString("ingredientName");
               System.out.println("*****"+holder);
               ingredients.add(holder);
           }
         
           Recipe JsonRecipe = new Recipe(iD, name, ingredients);
           
            
        }catch(UnsupportedEncodingException ex){
            
            
        }catch(IOException ex){
            
            
        }
        
        
        
        
        
    }

}
