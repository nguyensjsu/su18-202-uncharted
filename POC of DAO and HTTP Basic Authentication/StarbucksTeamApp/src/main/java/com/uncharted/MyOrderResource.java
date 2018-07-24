package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.uncharted.AuthenticationClass.bCheckIfAuthenticated;

@Path("order")
public class MyOrderResource {
    @GET
    @Path("/{custid}/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("custid") int custid, @PathParam("orderid") int orderid, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString, custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }

            OrderService os=new OrderService();
            OrderBO bo= os.getOrderById(orderid);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString= mapper.writeValueAsString(bo);
            return Response.ok("jsonString:"+jsonString).build();
            //return Response.ok().build();
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/describe/{custid}/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderDescriptionById(@PathParam("custid") int custid,@PathParam("orderid") int orderid, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString,custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }

            OrderService os=new OrderService();
            String desc= os.describeOrderDetails(orderid);
            return Response.ok("{\"Order Description\":\""+desc.substring(0,desc.length()-3)+"\"}").build();
            //return Response.ok().build();
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/cust/{custid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderByCustomer(@PathParam("custid") int custid, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString,custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }

            OrderService os=new OrderService();
            List<OrderBO> bo= os.getOrdersByCustomer(custid);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString= mapper.writeValueAsString(bo);
            return Response.ok("jsonString:"+jsonString).build();
            //return os.getOrdersByCustomer(id).get(0).getOrder_details();
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("/{custid}/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPayForOrder(@PathParam("custid") int custid,@PathParam("orderid") int orderid, @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString,custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }

            //call order service and fetch total
            OrderService os=new OrderService();
            OrderBO bo=os.getOrderById(orderid);
            double total=bo.getOrder_total();






            //**put below 2 in transaction
            //**call card service and try to reduce card balance if success only then allow payment







            //update payment datetime in orders
            if(os.payForOrder(orderid))
                return Response.ok("{\"Order Payment\":\"Successful\"}").build();
                //return "payment success";
            return Response.ok("{\"Order Payment\":\"Failed\"}").build();
            //return "payment failed";
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/{custid}/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("custid") int custid, @PathParam("orderid") int orderid,@HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString,custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }

            OrderService os=new OrderService();
            if(os.deleteOrder(orderid))
                return Response.ok("{\"Order Deletion\":\"Successful\"}").build();
                //return "delete success";
            return Response.ok("{\"Order Deletion\":\"Failed\"}").build();
            //return "delete failed";
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/cust/{custid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUpdateOrder(@PathParam("custid") int custid, @HeaderParam("authorization") String authString, String jsonBody) {
        try {
            if (!bCheckIfAuthenticated(authString,custid)) {
                //return "error:\"User not authenticated\"";
                return Response.status(401).build();
            }






            //**check if card added else don't allow







            JSONObject jsonObj = new JSONObject(jsonBody);
            JSONArray array = jsonObj.optJSONArray("menuitems");
            if (array == null) {
                return Response.status(422).build();
            }
            int[] items = new int[array.length()];

            for (int i = 0; i < array.length(); ++i) {
                items[i] = array.optInt(i);
            }
            OrderService os=new OrderService();
            if(os.insertOrUpdateOrder(custid,items))
                return Response.ok("{\"Order Insertion\":\"Success\"}").build();
            return Response.ok("{\"Order Insertion\":\"Failed\"}").build();
//            return "delete failed";
        }
        catch (Exception ex)
        {
            //return "error: "+ ex.getMessage();
            return Response.status(500).build();
        }
    }
}
