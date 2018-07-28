package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("mycustomer")
public class CustomerResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
        String jsonString=null;
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString,id)) {
                String json = "{error:User not authenticated}";

              return Response.status(401).build();
            }
            try {

                CustomerService service=new CustomerService();
                ObjectMapper mapper = new ObjectMapper();
                jsonString= mapper.writeValueAsString(service.getCustomer(id));
                System.out.println("jsonString:" + jsonString);

            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return Response.ok(jsonString).build();
    }


    @GET
    @Path("cutomerName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getbyName(@PathParam("name") String name)
    {
        String jsonString="";
        String temp="";
            try
            {

                CustomerService service=new CustomerService();
                ObjectMapper mapper = new ObjectMapper();
                ArrayList<CustomerBO> customerBOArrayList=service.getCustomerByUser(name);
                jsonString = mapper.writeValueAsString(customerBOArrayList);


            } catch (Exception e) {
                e.printStackTrace();
            }




        return Response.ok(jsonString).build();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCustomer(String jsonMessage)
    {
        int status=0;
        System.out.println(jsonMessage);
        JSONObject obj = new JSONObject(jsonMessage);
        boolean flag=false;

        CustomerBO customerBO=new CustomerBO();
        customerBO.setCustomer_name(obj.getString("customer_name"));
        customerBO.setCustomer_user_name(obj.getString("customer_user_name"));
        customerBO.setCustomer_password(obj.getString("customer_password"));
        customerBO.setCustomer_gender(obj.getString("customer_gender"));
        customerBO.setCustomer_birth(obj.getString("customer_birth"));


        try
        {
            CustomerService service=new CustomerService();
            flag=service.addCustomer(customerBO);

            if(flag)
                status=201;


        } catch (Exception ex)
        {
            ex.printStackTrace();
            status=500;
        }
      return Response.status(status).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int id,String jsonMessage)
    {

        int status=0;
        System.out.println(jsonMessage);
        JSONObject obj = new JSONObject(jsonMessage);
        boolean flag=false;

        CustomerBO customerBO=new CustomerBO();
        customerBO.setCustomer_name(obj.getString("customer_name"));
        customerBO.setCustomer_user_name(obj.getString("customer_user_name"));
        customerBO.setCustomer_password(obj.getString("customer_password"));
        customerBO.setCustomer_gender(obj.getString("customer_gender"));
        customerBO.setCustomer_birth(obj.getString("customer_birth"));
        customerBO.setCustomer_id(id);


        try
        {
            CustomerService service=new CustomerService();
            flag=service.updateCustomer(customerBO);

            if(flag)
                status=200;


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
    public Response deleteCustomer(@PathParam("id") int id, @HeaderParam("authorization") String authString)
    {

        boolean isDeleted=false;
        int status = 0;
        try
        {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString,id)) {
                String json = "{error:User not authenticated}";

                return Response.status(401).build();
            }
            CustomerService service=new CustomerService();
            isDeleted= service.deleteCustomer(id);

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
