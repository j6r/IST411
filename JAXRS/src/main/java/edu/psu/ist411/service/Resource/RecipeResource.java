/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ist411.service.Resource;

import edu.psu.ist411.service.Model.Ingredient;
import edu.psu.ist411.service.Model.Recipe;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

@Path("Recipe")
public class RecipeResource {
    Recipe rec = new Recipe();

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
     * @param inIngredient
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String getHtml() {
//        return "<html>\n"
//                + "    <head>\n"
//                + "        <title>Recipes</title>\n"
//                + "        <meta charset=\"UTF-8\">\n"
//                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
//                + "    </head>\n"
//                + "    <body>\n"
//                + "        <div>This is the recipes Page!</div>\n"
//                + "    </body>\n"
//                + "</html>";
//    }

    
    @GET
    @Path("{Ingredient}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("Ingredient") String inIngredient)
    {
        Ingredient ing = new Ingredient(inIngredient);
       // rec.addRecipeIngredient(ing);
        return Response.ok(ing.getIngredientName()).build();
        
        
//        if(ing == null)
//        .
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(ing).build();
    }
    /**
     * PUT method for updating or creating an instance of RecipesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHtml(JAXBElement<Ingredient> ingElement) {
        return Response.ok().build();
    }
}
