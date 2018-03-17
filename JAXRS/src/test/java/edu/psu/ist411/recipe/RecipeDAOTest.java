/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.recipe;

import edu.psu.ist411.recipe.RecipeDAO;
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
    
    public RecipeDAOTest() {
        this.testJsonToObj();
    }
    
    @Before
    public void setUp() {
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

        JsonObject json = instance.objToJSon(10,inName, ingredients);
        assertNotNull(instance);
        assertNotNull(json);
    }

    //@Test
    public void testJsonToObj() {
        System.out.println("jsonToObj");
        RecipeDAO instance = new RecipeDAO();
        instance.jsonToObj();
        
     
    }

}
