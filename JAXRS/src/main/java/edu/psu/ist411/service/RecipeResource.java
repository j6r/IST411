
package edu.psu.ist411.service;

import edu.psu.ist411.recipe.Recipe;
import edu.psu.ist411.recipe.RecipeDAO;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("recipes")
public class RecipeResource {

   @Context
   private UriInfo context;

   /**
    * Creates a new instance of RecipesResource
    */
   public RecipeResource() {
   }

   /**
    * Retrieves representation of an instance of
    * edu.psu.ist411.service.RecipesResource
    *
    * @return an instance of java.lang.String
    */
   @GET
   @Path("{id}")
   @Produces(MediaType.TEXT_PLAIN)
   public String getHtml(@PathParam("id") int id) {
      String response = "";
      RecipeDAO dao = null;

      try {
         dao = new RecipeDAO();

         Recipe recipe = dao.getRecipeById(id);
         if (recipe == null) {
            response = "Could not find a recipe with that ID";
         } else {
            
            StringBuilder ingredients = new StringBuilder();
            
            for (Iterator<String> iterator = recipe.getRecipeIngredients().iterator(); iterator.hasNext();) {
               String next = iterator.next();
               ingredients.append(next);
               if (iterator.hasNext()) {
                  ingredients.append(",");
               }
            }
            
            response = String.format("Recipe ID: %d%nRecipe name: %s%nRecipe Ingredients: %s%n"
                    + "Recipe description: %s", 
                    recipe.getRecipeID(), recipe.getName(), ingredients.toString(), recipe.getDescription());
         }

      } catch (IOException ex) {
         Logger.getLogger(RecipeResource.class.getName()).log(Level.SEVERE, null, ex);
         response = "An exception occurred :(";
      }
      
      return response;

//      return "<html>\n"
//              + "    <head>\n"
//              + "        <title>Recipes</title>\n"
//              + "        <meta charset=\"UTF-8\">\n"
//              + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
//              + "    </head>\n"
//              + "    <body>\n"
//              + "        <div>This is the recipes Page!</div>\n"
//              + "    </body>\n"
//              + "</html>";
   }

   /**
    * PUT method for updating or creating an instance of RecipesResource
    *
    * @param content representation for the resource
    */
   @PUT
   @Consumes(MediaType.TEXT_HTML)
   public void putHtml(String content) {
   }
}
