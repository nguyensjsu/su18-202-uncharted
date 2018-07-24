package com.uncharted;

import java.util.ArrayList;

public class CustomerService
{
    public CustomerBO getCustomer(int id)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();

        return customerDAO.getCustomer(id);
    }
    public ArrayList getCustomerByUser(String user)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();

        return customerDAO.getCustomerByUser(user);
    }

    public boolean addCustomer(CustomerBO customerBO)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();
        return customerDAO.addCustomer(customerBO);
    }
    public boolean updateCustomer(CustomerBO customerBO)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();
        return customerDAO.updateCustomer(customerBO);
    }
    public boolean deleteCustomer(int id)
    {
        CustomerDAO customerDAO=CustomerDAOFactory.getInstance();
        return customerDAO.deleteCustomer(id);
    }


}
