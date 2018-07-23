package com.uncharted;

import java.util.List;

public class OrderService {
    public boolean insertOrUpdateOrder(int customerid, int[] menuitems)
    {
        try {
            double total=0;



            //**fetch total from menuitems



            return OrderDAOFactory.getInstance().insertupdateOrder(customerid ,menuitems, total);
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


        //**call all menu items
        //**get order
        //**loop to create description


        return null;
    }

    public boolean payForOrder(int orderid)
    {
        try {
            return OrderDAOFactory.getInstance().payForOrder(orderid);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }

    public boolean deleteOrder(int orderid)
    {
        try {
            return OrderDAOFactory.getInstance().deleteOrder(orderid);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }
}
