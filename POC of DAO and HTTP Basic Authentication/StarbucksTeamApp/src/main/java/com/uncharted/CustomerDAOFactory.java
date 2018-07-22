package com.uncharted;

public class CustomerDAOFactory
{
    public static CustomerDAO getInstance()
    {
       return new CustomerDAOImpl();
    }
}
