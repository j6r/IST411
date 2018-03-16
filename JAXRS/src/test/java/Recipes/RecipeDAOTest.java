/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipes;

import java.util.ArrayList;
import javax.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Single-eye
 */
public class RecipeDAOTest {
    
    public RecipeDAOTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testObjToJSon() {
        System.out.println("objToJSon");
        RecipeDAO instance = new RecipeDAO();
        String inName = "lamb saag";
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient a = new Ingredient("Salt");
        Ingredient b = new Ingredient("Pepper");
        
        ingredients.add(a);
        ingredients.add(b);
        JsonObject json = instance.objToJSon(inName, ingredients);
        assertNotNull(instance);
        assertNotNull(json);
    }

    //@Test
    public void testJsonToObj() {
        System.out.println("jsonToObj");
        RecipeDAO instance = new RecipeDAO();
        instance.jsonToObj();
        fail("The test case is a prototype.");
    }

}
