package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("mymenu")
public class MyMenuResource
{
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMenuItems()
    {
        String jsonString="";
        String temp="";
        try
        {

            ObjectMapper mapper = new ObjectMapper();
            MenuService menuService=new MenuService();
            ArrayList<MenuBO> menuBOArrayList=menuService.getAllMenuItems();

            jsonString = mapper.writeValueAsString(menuBOArrayList);


        } catch (Exception e) {
            e.printStackTrace();
        }




        return Response.ok(jsonString).build();
    }
}
