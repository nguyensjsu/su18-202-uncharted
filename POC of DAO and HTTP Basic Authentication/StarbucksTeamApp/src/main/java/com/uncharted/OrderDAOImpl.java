package com.uncharted;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public OrderBO getOrder(int orderid) throws Exception {
        try {
            String strQuery = "select order_id, order_details, payment_date_time, total,customer_id from Orders where order_id=" + orderid + ";";
            ResultSet rs = DBConnectionSetup.getSelectQueryResult(strQuery);
            if (!rs.next()) {                            //if rs.next() returns false
                System.out.println("No records found");
                return null;
            } else {
                OrderBO ord;
                boolean flag = false;
                do {
                    if (!flag) {
                        ord = mSetOrderObject(rs);
                        flag = true;
                    } else {
                        System.out.println("More than 1 row with same id");
                        return null;
                    }
                } while (rs.next());
                return ord;
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            throw ex;
        }
        finally
        {
            DBConnectionSetup.closeConnection();
        }
    }

    public List<OrderBO> getAllOrders(int customerid) throws Exception {
        try {
            String strQuery = "select order_id, order_details, payment_date_time, total,customer_id from Orders where customer_id=" + customerid + ";";
            ResultSet rs = DBConnectionSetup.getSelectQueryResult(strQuery);
            if (!rs.next()) {                            //if rs.next() returns false
                System.out.println("No records found");
                return null;
            } else {
                ArrayList<OrderBO> ordli=new ArrayList<OrderBO>();
                do {
                    OrderBO ord = mSetOrderObject(rs);
                    ordli.add(ord);
                } while (rs.next());
                return ordli;
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            throw ex;
        }
        finally
        {
            DBConnectionSetup.closeConnection();
        }
    }

    public boolean insertupdateOrder(int customerid, int[] menuItems) throws Exception {
        return false;
    }

    public boolean payForOrder(int orderid) throws Exception {
        return false;
    }

    public boolean deleteOrder(int orderid) throws Exception {
        return false;
    }


    private OrderBO mSetOrderObject(ResultSet rs) throws Exception {

        OrderBO or = new OrderBO();
        or.setOrder_id(rs.getInt("order_id"));
        or.setOrder_details(rs.getString("order_details"));
        or.setOrder_pay_datetime(rs.getString("payment_date_time"));
        or.setOrder_total(rs.getDouble("total"));
        or.setCustomer_id(rs.getInt("customer_id"));
        return or;
    }

    /*public boolean insertEmployee(EmployeeBO bo) throws Exception
    {
        try {
            String strQuery = "insert into employee value (null,'" + bo.getName() + "')";
            if (DBConnectionSetup.getInsertUpdateDeleteQueryResult(strQuery))
                return true;
            return false;
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            DBConnectionSetup.closeConnection();
        }

    }
    */
}
