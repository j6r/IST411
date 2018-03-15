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

    private String name = " ";
    private ArrayList<Ingredient> ingredients = new ArrayList();

    public Recipe(String inName, Ingredient... args) {

        this.name = inName;

        for (Ingredient i : args) {

            int foundIngredient = this.findIngredient(i);

            if (foundIngredient < 0) {
                this.getIngredients().add(i);
            }
        }

    }

    private String getName() {

        return name;
    }

    private ArrayList getIngredients() {

        return this.ingredients;

    }

    private int findIngredient(Ingredient inIngredient) {

        return this.ingredients.indexOf(inIngredient);

    }

    private int findIngredient(String inName) {

        for (int i = 0; i < this.ingredients.size(); i++) {
            if (this.ingredients.get(i).getDishIngredients().contains(name)) {
                return i;
            }
        }
        return -1;
    }

    public void queryIngredients(String inName) {

     
    }

}
