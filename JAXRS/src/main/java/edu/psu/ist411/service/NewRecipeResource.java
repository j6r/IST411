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
    * @return an instance of java.lang.String
    */
   @POST
   @Produces(MediaType.TEXT_HTML)
   public String getHtml(@FormParam("recipeName") String recipeName) {
      String result = "";
      Recipe recipe = null;
      RecipeDAO dao = null;
      HashSet<String> ingredients = new HashSet<>();

      try {
         dao = new RecipeDAO();
         recipe = dao.addRecipe(recipeName, ingredients);
         result = String.format(RETURN_TEMPLATE, recipe.getRecipeID());

      } catch (IOException ex) {
         Logger.getLogger(NewRecipeResource.class.getName()).log(Level.SEVERE, null, ex);
      }

      return result;
   }

}
