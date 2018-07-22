package com.uncharted;

public class CustomerService
{
    public CustomerBO getCustomer(int id)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();

        return customerDAO.getCustomer(id);
    }
}
