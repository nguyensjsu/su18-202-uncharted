package com.uncharted;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("order")
public class MyOrderResource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOrderById(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            /*if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }*/

            OrderService os=new OrderService();
            return os.getOrderById(id).getOrder_details();
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }


}
