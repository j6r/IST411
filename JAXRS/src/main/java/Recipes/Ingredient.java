/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipes;

/**
 *
 * @author Single-eye
 */
public class Ingredient {
    
private String ingredientName; 
    
 public Ingredient(String inIngredient){
     
     this.ingredientName = inIngredient;
     
 }   
public String getIngredientName(){
    
    return this.ingredientName;
}

@Override
public String toString(){

    return this.getIngredientName();
    

} 
}
