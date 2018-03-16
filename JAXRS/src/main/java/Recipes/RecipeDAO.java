package Recipes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Single-eye
 */
public class RecipeDAO {

    public RecipeDAO() {

    }

    public JsonObject objToJSon(String inName, ArrayList<Ingredient> ingredients) {
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
        for (Ingredient ingredient : ingredients) {
            ingredientBuilder.add("ingredientName", ingredient.getIngredientName());
            /*
            {"ingredientName","SALT"}
             */
            arrayBuilder.add(ingredientBuilder);
        }
        JsonArray ingredientsArray = arrayBuilder.build();

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

    }

}
