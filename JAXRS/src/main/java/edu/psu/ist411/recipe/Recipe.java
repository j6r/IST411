/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.recipe;

import java.util.HashSet;

/**
 *
 * @author Single-eye
 */
public class Recipe {

    private int ID;
    private String name = " ";
    private HashSet<String> recipeIngredientList = new HashSet();

    public Recipe(int recipeID, String inName, String... ingredients) {

        this.ID = recipeID;
        this.name = inName.toLowerCase();

        for (String i : ingredients) {
           
                this.recipeIngredientList.add(i.toLowerCase());

        }

    }

    public Recipe(int inID, String inName, HashSet ingredients) {
        this.ID = inID;
        this.name = inName.toLowerCase();
        this.recipeIngredientList = ingredients;
    }

    public String getName() {
        return name;
    }
    public void setName(String inName){
        
        name = inName;
        
    }

    public int getRecipeID() {
        return this.ID;

    }

    public void addRecipeIngredient(String inIngredient) {

        this.recipeIngredientList.add(inIngredient.toLowerCase());
    }

    public void deleteRecipeIngredient(String inIngredient) {

        recipeIngredientList.remove(inIngredient.toLowerCase());
    }

    public String getRecipeIngredient(String inIngredient) {

        String result = null;

        for (String i : this.recipeIngredientList) {

            if(this.recipeIngredientList.contains(inIngredient)){
                
                result = i;
                System.out.println("*****"+i);
            }
   
        }
        return result;
    }

}
