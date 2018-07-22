package com.uncharted;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class CustomerResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            return "success";
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }
}
