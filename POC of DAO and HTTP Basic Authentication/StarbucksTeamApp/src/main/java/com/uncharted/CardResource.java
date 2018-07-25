package com.uncharted;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("card")
public class CardResource {

    @GET
    @Path("/{custid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardByCustID(@PathParam("custid") int custid, @HeaderParam("authorization") String authString) {
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString,custid)) {
                return Response.status(401).build();
            }

            CardService cardService=new CardService();

            ObjectMapper mapper = new ObjectMapper();
            CardBO cbo=cardService.getCardByCust(custid);
            if(cbo.getCard_code()==null)
                return Response.ok("{\"message\":\"no card assigned\"}").build();

            String jsonString= mapper.writeValueAsString(cbo);
            System.out.println("jsonString:" + jsonString);
            return Response.ok(jsonString).build();
        }
        catch (Exception ex)
        {
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/{custid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCustomer(@PathParam("custid") int custid,@HeaderParam("authorization") String authString, String jsonMsg)
    {
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString,custid)) {
                return Response.status(401).build();
            }

            JSONObject object = new JSONObject(jsonMsg);
            boolean flag=false;

            CardBO cBO=new CardBO();
            cBO.setCard_id(object.getInt("card_id"));
            cBO.setCard_code(object.getString("card_code"));
            cBO.setCard_balance(object.getDouble("card_balance"));
            cBO.setCustomer_id(object.getInt("customer_id"));


            try
            {
                CardService service=new CardService();
                flag=service.addCard(cBO);

                if(flag)
                    return Response.status(201).build();
                else
                    return Response.status(500).build();

            } catch (Exception ex)
            {
                ex.printStackTrace();
                return Response.status(500).build();
            }
        }
        catch (Exception ex)
        {
            return Response.status(500).build();
        }
    }


    @PUT
    @Path("/{custid}/{cardid}/{balance}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCardBalance(@PathParam("custid") int custid,@PathParam("cardid") int cardid,@PathParam("balance") double balance,@HeaderParam("authorization") String authString)
    {
        try {
            if (!AuthenticationClass.bCheckIfAuthenticated(authString,custid)) {
                return Response.status(401).build();
            }
            boolean flag=false;

            try
            {
                CardService service=new CardService();
                flag=service.updateCardBalance(cardid,balance);

                if(flag)
                    return Response.status(201).build();
                else
                    return Response.status(500).build();

            } catch (Exception ex)
            {
                ex.printStackTrace();
                return Response.status(500).build();
            }
        }
        catch (Exception ex)
        {
            return Response.status(500).build();
        }
    }

}
