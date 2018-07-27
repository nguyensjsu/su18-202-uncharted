package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("mymenu")
public class MyMenuResource
{
    @GET
   // @Path("/")
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

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMenu(@PathParam("id") int id,String jsonMessage)
    {

        int status=0;
        System.out.println(jsonMessage);
        JSONObject obj = new JSONObject(jsonMessage);
        boolean flag=false;

        MenuBO menuBO=new MenuBO();
        menuBO.setItemID(id);
        menuBO.setStoreID(obj.getInt("storeID"));
        menuBO.setCateogory(obj.getString("cateogory"));
        menuBO.setItemPrice(obj.getInt("itemPrice"));
        menuBO.setItemName(obj.getString("itemName"));



        try
        {
            MenuService service=new MenuService();
            flag=service.updateItem(menuBO);

            if(flag)
                status=200;


        } catch (Exception ex)
        {
            ex.printStackTrace();
            status=500;
        }
        return Response.status(status).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertMenu(String jsonMessage)
    {
        int status=0;
        try
        {

        System.out.println(jsonMessage);
        JSONObject obj = new JSONObject(jsonMessage);
        boolean flag=false;

        MenuBO menuBO=new MenuBO();
        ////menuBO.setItemID(obj.getInt("item_id"));
        menuBO.setStoreID(obj.getInt("store_id"));
        menuBO.setCateogory(obj.getString("category"));
        menuBO.setItemPrice(obj.getInt("item_price"));
        menuBO.setItemName(obj.getString("item_name"));



            MenuService service=new MenuService();
            flag=service.additems(menuBO);

            if(flag)
                status=201;


        } catch (Exception ex)
        {
            ex.printStackTrace();
            status=500;
        }
        return Response.status(status).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int id)
    {

        boolean isDeleted=false;
        int status = 0;
        try
        {

            MenuService service=new MenuService();
            isDeleted= service.deleteitem(id);

            if(isDeleted)
                status=200;
            else
                status=404;


        } catch (Exception ex)
        {
            ex.printStackTrace();
            status=500;
        }
        return Response.status(status).build();
    }

}
