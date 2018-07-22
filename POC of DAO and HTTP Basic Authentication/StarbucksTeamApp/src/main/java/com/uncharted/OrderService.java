package com.uncharted;

import java.util.List;

public class OrderService {
    public boolean insertOrUpdateOrder(int customerid, int[] menuitems)
    {
        try {
            return OrderDAOFactory.getInstance().insertupdateOrder(customerid ,menuitems);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }

    public OrderBO getOrderById(int orderid)
    {
        try {
            return OrderDAOFactory.getInstance().getOrder(orderid);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    public List<OrderBO> getOrdersByCustomer(int customerid)
    {
        try {
            return OrderDAOFactory.getInstance().getAllOrders(customerid);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }

    }

    public String describeOrderDetails(int orderid)
    {
        return null;
    }

    public String payForOrder(int orderid)
    {
        return null;
    }

    public String deleteOrder(int orderid)
    {
        return null;
    }
}
