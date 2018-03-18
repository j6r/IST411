/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.recipe;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Single-eye
 */
public class RecipeList {
    
    private HashSet<Recipe> recipeBook = new HashSet();
    
    
    
public RecipeList() {
        
 
   
}
public void addRecipe(Recipe inRecipe){
    
    
     this.recipeBook.add(inRecipe);
    
} 
public void addRecipe(HashSet<Recipe> inRecipes){
    
        this.recipeBook.addAll(inRecipes);
    
}
 
   /**
    * Deletes the recipe with the specified ID if it exists
    *
    * @param id the ID of the recipe to delete
    */
   public void deleteRecipe(int id) {
      for (Iterator<Recipe> iterator = recipeBook.iterator(); iterator.hasNext();) {
         Recipe next = iterator.next();
         if (next.getRecipeID() == id) {
            iterator.remove();
            break;
         }
      }
//      saveAll();
   }
   /**
    * Returns the specified recipe
    *
    * @param id the ID of the recipe
    * @return the recipe or null if not found
    */
   public Recipe getRecipeById(int id) {
        for (Recipe next : recipeBook) {
            if (next.getRecipeID() == id) {
                return next;
            }
        }

      return null;
   }
   public void modifyRecipe(int id, Recipe newRecipe){
       
         for (Recipe next : recipeBook) {
          if(next.getRecipeID()== id){
              
          }
             
        }

       
       
   }
   public HashSet<Recipe> retrieveRecipeList(){
       
       return this.recipeBook;
   }
 

}
