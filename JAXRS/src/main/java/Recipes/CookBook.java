/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Single-eye
 */
public class CookBook {
    
private ArrayList<Recipe> recipes = new ArrayList();

public CookBook(Recipe inRecipe){
    this.recipes.add(inRecipe);
}

    /**
     * @return the recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    
    
}
