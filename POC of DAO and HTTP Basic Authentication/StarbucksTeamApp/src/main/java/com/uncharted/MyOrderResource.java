package com.uncharted;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.uncharted.AuthenticationClass.bCheckIfAuthenticated;

@Path("order")
public class MyOrderResource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOrderById(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            OrderService os=new OrderService();
            //******see what to return here
            return os.getOrderById(id).getOrder_details();
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }

    @GET
    @Path("/cust/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOrderByCustomer(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            OrderService os=new OrderService();
            //******see what to return here
            return os.getOrdersByCustomer(id).get(0).getOrder_details();
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String putPayForOrder(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            //**call order service and fetch total
            //**call card service and reduce card balance

            OrderService os=new OrderService();
            if(os.payForOrder(id))
                return "payment success";
            //******see what to return here
            return "payment failed";
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteOrder(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            OrderService os=new OrderService();
            if(os.deleteOrder(id))
                return "delete success";
            //******see what to return here
            return "delete failed";
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String insertUpdateOrder(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            //check if card added else dont allow

            //**check if row with "" payment datetime exists
            //then update else insert

            /*OrderService os=new OrderService();
            if(os.deleteOrder(id))
                return "delete success";
            //******see what to return here*/
            return "delete failed";
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }
}
