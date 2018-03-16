/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipes;

import java.util.ArrayList;

/**
 *
 * @author Single-eye
 */
public class Recipe {

    private int ID;
    private String name = " ";
    private ArrayList<Ingredient> recipeIngredientList = new ArrayList();

    public Recipe(int recipeID, String inName, Ingredient... ingredients) {

        this.ID = recipeID;
        this.name = inName;

        for (Ingredient i : ingredients) {

            int foundIngredient = this.findRecipeIngredient(i);

            if (foundIngredient < 0) {
                this.recipeIngredientList.add(i);
            }
        }

    }
    public Recipe(String inName, ArrayList ingredients){
        
        this.name = inName;
        this.recipeIngredientList = ingredients;  
    }
      private String getName() {

        return name;
    }
    public int getRecipeID(){
        return this.ID;
        
    }
    private int findRecipeIngredient(Ingredient inIngredient) {

        return this.recipeIngredientList.indexOf(inIngredient);

    }

    private int findRecipeIngredient(String inName) {

        for (int i = 0; i < this.recipeIngredientList.size(); i++) {
            if (this.recipeIngredientList.get(i).getDishIngredients().contains(name)) {
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList getIngredientsList() {

        return this.recipeIngredientList;

    }
   
   
}
