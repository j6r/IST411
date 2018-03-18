
package edu.psu.ist411.service.Model;


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
    

