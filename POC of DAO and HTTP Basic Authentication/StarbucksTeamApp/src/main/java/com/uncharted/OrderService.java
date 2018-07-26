package com.uncharted;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public boolean insertOrUpdateOrder(int customerid, final int[] menuitems)
    {
        try {
            double total=0;

            //fetch total from menuitems
            MenuService ms=new MenuService();
            ArrayList<MenuBO> arrMenuBO=ms.getAllMenuItems();

            for(int i=0;i<menuitems.length;i++)
            {
                final int itemid = menuitems[i];
                MenuBO b=null; //arrMenuBO.stream().filter(x -> itemid == x.getItemID()).findFirst().orElse(null);
                total+=b.getItemPrice();
            }

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

    public String describeOrderDetails(int orderid) throws Exception
    {
        //get order
        OrderBO bo=OrderDAOFactory.getInstance().getOrder(orderid);
        String strmenuitems=bo.getOrder_details();
        String[] items=strmenuitems.split(",");
        ArrayList<Integer> mi=null;//new ArrayList<>();
        for(int i=0;i<items.length;i++)
        {
            try{
                mi.add(Integer.parseInt(items[i]));
            }
            catch (Exception ex)
            {
            }
        }
        int[] menuitems =null;// mi.stream().mapToInt(i -> i).toArray();

        //call all menu items loop to create description
        MenuService ms=new MenuService();
        ArrayList<MenuBO> arrMenuBO=ms.getAllMenuItems();

        String desc="";
        for(int i=0;i<menuitems.length;i++)
        {
            final int itemid = menuitems[i];
            MenuBO b=null;// arrMenuBO.stream().filter(x -> itemid == x.getItemID()).findFirst().orElse(null);
            desc+=b.getItemName()+" + ";
        }
        return desc;
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
