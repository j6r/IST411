/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.recipe;

import edu.psu.ist411.recipe.RecipeDAO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import javax.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Single-eye
 */
public class RecipeDAOTest {

   private static final String DATA_FILE = "Recipe.json";

   public RecipeDAOTest() {
   }

   @Before
   public void setUp() throws Exception {
      Files.delete(Paths.get(DATA_FILE));
   }

   @Test
   public void testObjToJSon() {
      System.out.println("objToJSon");
      RecipeDAO instance = new RecipeDAO();
      String inName = "lamb saag";
      HashSet<String> ingredients = new HashSet();
      ingredients.add("Salt");
      ingredients.add("Pepper");
      ingredients.add("Love");

      JsonObject json = instance.objToJSon(10, inName, ingredients);
      assertNotNull(instance);
      assertNotNull(json);
   }

   //@Test
   public void testJsonToObj() {
      System.out.println("jsonToObj");
      RecipeDAO instance = new RecipeDAO();
      instance.jsonToObj();

   }

   @Test
   public void testAddRecipe() throws Exception {

      RecipeDAO instance = new RecipeDAO();

      HashSet<String> ingredients = new HashSet();
      ingredients.add("Salt");
      ingredients.add("Pepper");
      ingredients.add("Love");

      Recipe recipe1 = instance.addRecipe("test 1", ingredients);
      Recipe recipe2 = instance.addRecipe("test 2", ingredients);

      assertEquals(recipe1.getName(), instance.getRecipeById(recipe1.getRecipeID()).getName());
      assertEquals(recipe2.getName(), instance.getRecipeById(recipe2.getRecipeID()).getName());
   }

   @Test
   public void testDeleteRecipe() throws Exception {

      RecipeDAO instance = new RecipeDAO();

      HashSet<String> ingredients = new HashSet();
      ingredients.add("Salt");
      ingredients.add("Pepper");
      ingredients.add("Love");

      Recipe recipe1 = instance.addRecipe("test 1", ingredients);

      instance.deleteRecipe(recipe1.getRecipeID());

      assertNull(instance.getRecipeById(recipe1.getRecipeID()));
   }

   @Test
   public void testUpdateRecipe() throws Exception {
      final String expectedName = "new name";

      RecipeDAO instance = new RecipeDAO();

      HashSet<String> ingredients = new HashSet();
      ingredients.add("Salt");
      ingredients.add("Pepper");
      ingredients.add("Love");

      Recipe recipe1 = instance.addRecipe("test 1", ingredients);
      
      recipe1.setName(expectedName);
      instance.saveRecipe(recipe1);

      assertEquals(expectedName, instance.getRecipeById(recipe1.getRecipeID()).getName());
   }

}
