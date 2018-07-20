package com.sourabh;

import sun.misc.BASE64Decoder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


@Path("myresource")
public class MyResource {
    @GET
    @Path("/dataentry/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("id") int id,  @HeaderParam("authorization") String authString) {
        try {
            if (!bCheckIfAuthenticated(authString)) {
                return "error:\"User not authenticated\"";
            }

            EmployeeBO bo = new EmployeeBO();
            bo.setName("tarun");
            EmployeeService es = new EmployeeService();
            return "Got it! " + id + "Employee no 3 is: " + es.getEmployeeById(3).getName() + " ,,new employee:" + es.registerNewEmployee(bo);
        }
        catch (Exception ex)
        {
            return "error: "+ ex.getMessage();
        }
    }

    private boolean bCheckIfAuthenticated(String authString) throws IOException
    {
        String decodedAuth;
        String[] authList = authString.split("\\s+");
        String authInfo = authList[1];
        byte[] byteArr = null;
        try {
            byteArr = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException ex) {
            throw ex;
        }
        decodedAuth = new String(byteArr);
        System.out.println(decodedAuth);

        //validation code

        return true;
    }
}