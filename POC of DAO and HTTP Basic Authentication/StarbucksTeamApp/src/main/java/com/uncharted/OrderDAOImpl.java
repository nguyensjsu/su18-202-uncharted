package com.uncharted;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String TABLENAME="Orders";
    private static final String COL_order_id="order_id";
    private static final String COL_order_details="order_details";
    private static final String COL_payment_date_time="payment_date_time";
    private static final String COL_total="total";
    private static final String COL_customer_id="customer_id";

    public OrderBO getOrder(int orderid) throws Exception {
        try {
            String strQuery = "select "+COL_order_id+", "+COL_order_details+", "+COL_payment_date_time+", "+COL_total+","+COL_customer_id+" from "+TABLENAME+" where "+COL_order_id+"=" + orderid + ";";
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
            String strQuery = "select "+COL_order_id+", "+COL_order_details+", "+COL_payment_date_time+", "+COL_total+","+COL_customer_id+" from "+TABLENAME+" where "+COL_customer_id+"=" + customerid + ";";
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
        try {
            String strQuery = "update "+TABLENAME+" set "+COL_payment_date_time+"='"+new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())+"' where "+COL_order_id+"=" + orderid + ";";
            return  DBConnectionSetup.getInsertUpdateDeleteQueryResult(strQuery);
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

    public boolean deleteOrder(int orderid) throws Exception {
        try {
            String strQuery = "delete from "+TABLENAME+" where "+COL_order_id+"=" + orderid + ";";
            return  DBConnectionSetup.getInsertUpdateDeleteQueryResult(strQuery);
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


    private OrderBO mSetOrderObject(ResultSet rs) throws Exception {

        OrderBO or = new OrderBO();
        or.setOrder_id(rs.getInt(COL_order_id));
        or.setOrder_details(rs.getString(COL_order_details));
        or.setOrder_pay_datetime(rs.getString(COL_payment_date_time));
        or.setOrder_total(rs.getDouble(COL_total));
        or.setCustomer_id(rs.getInt(COL_customer_id));
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
