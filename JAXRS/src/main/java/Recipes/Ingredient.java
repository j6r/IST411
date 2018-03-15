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
    
private String dishIngredient; 
    
 public Ingredient(String inIngredient){
     
     this.dishIngredient = inIngredient;
     
 }   
public String getDishIngredients(){
    
    return this.dishIngredient;
}
    
}
