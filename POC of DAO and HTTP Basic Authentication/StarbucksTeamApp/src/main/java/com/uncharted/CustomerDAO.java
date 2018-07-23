package com.uncharted;

public interface CustomerDAO {

    CustomerBO getCustomer(int id);

    public boolean addCustomer(CustomerBO customerBO);

    public boolean updateCustomer(CustomerBO customerBO);

    public boolean deleteCustomer(int id);

    public boolean getCustomerByUserAndPassword(String user,String password);

}
