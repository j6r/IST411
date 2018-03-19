package edu.psu.ist411.service;

import edu.psu.ist411.recipe.Recipe;
import edu.psu.ist411.recipe.RecipeDAO;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * NewRecipeResource is a RESTFul web service for creating new recipes.
 *
 */
@Path("recipes/new")
public class NewRecipeResource {

   @Context
   private UriInfo context;

   private static final String RETURN_TEMPLATE = "<html><head></head>"
           + "<body><div>Your recipe was created successfully!"
           + "To view it click <a href='/JAXRS/webresources/recipes/%d'>here</a>.";

   /**
    * Creates a new instance of NewRecipeResource
    */
   public NewRecipeResource() {
   }

   /**
    * Retrieves representation of an instance of
    * edu.psu.ist411.service.NewRecipeResource
    *
    * @param recipeName
    * @param recipeDescription
    * @return an instance of java.lang.String
    */
   @POST
   @Produces(MediaType.TEXT_HTML)
   public String getHtml(@FormParam("recipeName") String recipeName,
           @FormParam("recipeDescription") String recipeDescription,
           @FormParam("recipeIngredients") String recipeIngredients) {
      String result = "";
      Recipe recipe = null;
      RecipeDAO dao = null;
      HashSet<String> ingredients = new HashSet<>();

      try {
         dao = new RecipeDAO();
         
         for (String ingredient : recipeIngredients.split("/")) {
            ingredients.add(ingredient.toLowerCase());
         }
         
         recipe = dao.addRecipe(recipeName, recipeDescription, ingredients);
         result = String.format(RETURN_TEMPLATE, recipe.getRecipeID());

      } catch (IOException ex) {
         Logger.getLogger(NewRecipeResource.class.getName()).log(Level.SEVERE, null, ex);
      }

      return result;
   }
/*
    @PUT
   @Produces(MediaType.TEXT_HTML)
    public String createRecipe(@FormParam("recipeId") String recipeId, @FormParam("newRecipeName") String newRecipeName) {
        String response = "";
        RecipeDAO dao = null;
        if (recipeId == null || newRecipeName == null) {
            //status code 204
//            return Response.noContent()
//                    .build();
        } else {
            try {
             dao = new RecipeDAO();
             
            Recipe recipe = dao.getRecipeById(Integer.parseInt(recipeId));
            if (recipe != null) {
                recipe.setName(newRecipeName);
                    dao.saveRecipe(recipe);
            }
                //status code 201
//                return Response.created(context.getAbsolutePath())
//                        .build();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }*/
}
