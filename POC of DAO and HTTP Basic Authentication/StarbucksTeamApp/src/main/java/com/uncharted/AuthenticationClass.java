package com.uncharted;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class AuthenticationClass
{
    static CustomerBO customerBO=null;
    public static boolean bCheckIfAuthenticated(String authString,int id) throws IOException
    {
        boolean validCustomer=false;
        String decodedAuth;
        //String[] authList = authString.split("\\s+");
        String authInfo =authString;
        byte[] byteArr = null;
        try {
            byteArr = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException ex) {
            throw ex;
        }
        decodedAuth = new String(byteArr);
        System.out.println(decodedAuth);


        validCustomer=checkIfValidCustomer(decodedAuth,id);

        return validCustomer;
    }
    public static boolean checkIfValidCustomer(String userAndPassword,int id)
    {
        boolean flag=false;
        CustomerDAO customerDAO=null;
        customerDAO=CustomerDAOFactory.getInstance();
        customerBO=customerDAO.getCustomer(id);
        String user=null;
        String password=null;
        String[] data=null;
        if(customerBO!=null) {
            user= customerBO.getCustomer_user_name();
            password  = customerBO.getCustomer_password();
            data= userAndPassword.split(":");
        }
        else
        {
            return flag;
        }
        if(user.equals(data[0])&&password.equals(data[1]))
            flag=true;



        return flag;
    }
}
