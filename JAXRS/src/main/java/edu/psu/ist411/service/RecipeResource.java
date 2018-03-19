package edu.psu.ist411.service;

import edu.psu.ist411.recipe.Recipe;
import edu.psu.ist411.recipe.RecipeDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.eclipse.persistence.sessions.remote.corba.sun.TransporterHelper.id;

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
                response = String.format("Recipe ID: %d%nRecipe name: %s",
                        recipe.getRecipeID(), recipe.getName());
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
    
   @Produces(MediaType.TEXT_HTML)
    //@Consumes(MediaType.TEXT_PLAIN)
    public Response createRecipe(@FormParam("recipeId") String recipeId, @FormParam("newRecipeName") String newRecipeName) {
        String response = "";
        RecipeDAO dao = null;
        if (recipeId == null || newRecipeName == null) {
            //status code 204
            return Response.noContent()
                    .build();
        } else {
            try {
                 dao = new RecipeDAO();

                Recipe recipe = dao.getRecipeById(Integer.parseInt(recipeId));
                if (recipe != null) {
                    recipe.setName(newRecipeName);
                        dao.saveRecipe(recipe);
                }
                //status code 201
                return Response.created(context.getAbsolutePath())
                        .build();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

            return Response.accepted().build();
    }
    @DELETE
    @Path("{deleteId}")   
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("deleteId") int i) {
        RecipeDAO dao = null;
       String response = "";
        try {
            dao = new RecipeDAO();
            dao.deleteRecipe(i);
                response = String.format("Recipe ID: %d was deleted",
                        i);
            return response;
        } catch (Exception e) {
            Logger.getLogger(RecipeResource.class.getName()).log(Level.SEVERE, null, e);
            response = "An exception occurred :(";
            e.printStackTrace();
        }
        return response;
    }

}
