package com.sourabh;

import java.util.List;

public interface OrderDAO {
    //GET
    OrderBO getOrder(int orderid) throws Exception;
    List<OrderBO> getAllOrders(int customerid) throws Exception;

    //POST
    //if unpaid row exists then update
    boolean insertupdateOrder(int customerid, int[] menuItems) throws Exception;
    boolean payForOrder(int orderid) throws Exception;

    //DELETE
    boolean deleteOrder(int orderid) throws Exception;
}
