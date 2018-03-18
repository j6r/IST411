/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.recipe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Single-eye
 */
public class RecipeTest {
    
    Recipe recipe;
    
    public RecipeTest() {
        
        this.setUp();
   
    }
    
    @Before
    public void setUp() {
        
       this.recipe = new Recipe(8,"Bacon", "pepper","Salt","love");
        
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Recipe instance = recipe;
        String expResult = "bacon";
        String result = instance.getName();
        System.out.println(result);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetRecipeID() {
        System.out.println("getRecipeID");
        Recipe instance = recipe;
        int expResult = 8;
        int result = instance.getRecipeID();
        assertEquals(expResult, result);
        System.out.println(expResult);
    }

    @Test
    public void testAddRecipeIngredient() {
        System.out.println("addRecipeIngredient");
        String inIngredient = "Curry";
        Recipe instance = recipe;
        instance.addRecipeIngredient(inIngredient);
      
    }

    @Test
    public void testDeleteRecipeIngredient() {
        System.out.println("deleteRecipeIngredient");
        String inIngredient = "Salt";
        Recipe instance = recipe;
        instance.deleteRecipeIngredient(inIngredient);
        
    }

  
    public void testGetRecipeIngredient() {
        System.out.println("findRecipeIngredient");
        String inIngredient = "salt";
        Recipe instance = recipe;
        String expResult = "salt";
        String result = instance.getRecipeIngredient(inIngredient);
        assertEquals(expResult, result);

    }

    @Test
    public void testEquals() {
       Recipe recipe2 = new Recipe(10,"sugar");
       Recipe recipe3 = new Recipe(7,"Bacon", "pepper","Salt","love");
       
       assertEquals(recipe, recipe2);
       assertNotEquals(recipe, recipe3);
    }
}
