package com.uncharted;

public class OrderDAOFactory {
    public static OrderDAO getInstance()
    {
        return new OrderDAOImpl();
    }
}
