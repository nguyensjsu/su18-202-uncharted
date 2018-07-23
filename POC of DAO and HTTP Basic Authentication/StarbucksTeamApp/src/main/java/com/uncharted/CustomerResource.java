package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("mycustomer")
public class CustomerResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        //JSONObject jsonObj = null;
        String jsonString=null;
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString)) {
                String json = "{error:User not authenticated}";

                Response.status(401).build();
            }
            try {

                ObjectMapper mapper = new ObjectMapper();
                jsonString= mapper.writeValueAsString(AuthenticationClass.customerBO);
                System.out.println("jsonString:" + jsonString);


               // jsonObj = new JSON(jsonString);

            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return Response.ok(jsonString).build();
    }
}
