package com.uncharted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO
{

    public CustomerBO getCustomer(int customer_id)
    {
        CustomerBO customer=new CustomerBO();
        Connection connection=null;
        Statement st=null;
        ResultSet rs=null;

        String query="select * from customer where customer_id="+customer_id;

        try
        {
            connection =DBConnection.getConnection();
            st=connection.createStatement();
            rs=st.executeQuery(query);
            if(rs.next())
            {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_birth(rs.getString("customer_birth"));
                customer.setCustomer_gender(rs.getString("customer_gender"));
                customer.setCustomer_user_name(rs.getString("customer_user_name"));
                customer.setCustomer_password(rs.getString("customer_password"));
            }
            else
            {
                System.out.println("Empty value in result set");
            }



        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally {
            try
            {
                 if(rs!=null)
                     rs.close();

                 if(st!=null)
                     st.close();

                 if(connection!=null)
                     connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public boolean getCustomerByUserAndPassword(String user,String password)
    {
        CustomerBO customer=new CustomerBO();
        Connection connection=null;
        Statement st=null;
        ResultSet rs=null;
        PreparedStatement statementObject=null;
        boolean flag=false;

        String query="select * from customer where customer_user_name=? and customer_password=?";

        try
        {
            connection =DBConnection.getConnection();
            statementObject=connection.prepareStatement(query);
            statementObject.setString(1,user);
            statementObject.setString(2,password);
            statementObject.executeQuery();
            System.out.println("Query is:"+query);

            if(rs.next())
            {
                flag=true;
            }
            else
            {
                System.out.println("Empty value in result set");
            }



        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally {
            try
            {
                if(rs!=null)
                    rs.close();

                if(st!=null)
                    st.close();

                if(connection!=null)
                    connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public ArrayList getCustomerByUser(String user) {

        ArrayList<CustomerBO> customerList=new ArrayList<CustomerBO>();

        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;

        String query="select * from customer where customer_name = ?";

        try
        {
            connection =DBConnection.getConnection();
            st=connection.prepareStatement(query);
            st.setString(1,user);
            rs=st.executeQuery();
            while(rs.next())
            {
                CustomerBO customer=new CustomerBO();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_birth(rs.getString("customer_birth"));
                customer.setCustomer_gender(rs.getString("customer_gender"));
                customer.setCustomer_user_name(rs.getString("customer_user_name"));
                customer.setCustomer_password(rs.getString("customer_password"));
                customerList.add(customer);
            }




        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally {
            try
            {
                if(rs!=null)
                    rs.close();

                if(st!=null)
                    st.close();

                if(connection!=null)
                    connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return customerList;
    }


    public boolean addCustomer(CustomerBO customerBO)
    {

        Connection connection=null;
        PreparedStatement statementObject=null;
        boolean flagForRegistration=false;

        String query="INSERT INTO customer(customer_id,customer_name,customer_birth,customer_gender,customer_user_name,customer_password) VALUES (?,?,?,?,?,?)";

        try
        {
            connection =DBConnection.getConnection();
            statementObject=connection.prepareStatement(query);
            System.out.println("Query is:"+query);
            statementObject = connection.prepareStatement(query);
            statementObject.setInt(1, customerBO.getCustomer_id());
            statementObject.setString(2, customerBO.getCustomer_name());
            statementObject.setString(3, customerBO.getCustomer_birth());
            statementObject.setString(4, customerBO.getCustomer_gender());
            statementObject.setString(5,customerBO.getCustomer_user_name());
            statementObject.setString(6, customerBO.getCustomer_password());

            flagForRegistration= statementObject.execute();


        }
        catch (Exception ex) {
        ex.printStackTrace();
        }
        finally {
            try
            {

                if(statementObject!=null)
                    statementObject.close();

                if(connection!=null)
                    connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return flagForRegistration;
    }


    public boolean updateCustomer(CustomerBO customerBO)
    {

        Connection connection=null;
        PreparedStatement statementObject=null;
        boolean flagForUpdate=false;

        String query="update customer SET customer_name=?,customer_birth=? ,customer_gender=?,customer_user_name=?,customer_password=? where customer_id=?";

        try
        {

            connection =DBConnection.getConnection();
            statementObject = connection.prepareStatement(query);

            statementObject.setString(1, customerBO.getCustomer_name());
            statementObject.setString(2, customerBO.getCustomer_birth());
            statementObject.setString(3, customerBO.getCustomer_gender());
            statementObject.setString(4,customerBO.getCustomer_user_name());
            statementObject.setString(5, customerBO.getCustomer_password());
            statementObject.setInt(6, customerBO.getCustomer_id());

            flagForUpdate= statementObject.execute();


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try
            {

                if(statementObject!=null)
                    statementObject.close();

                if(connection!=null)
                    connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return flagForUpdate;
    }

    public boolean deleteCustomer(int id)
    {

        Connection connection=null;
        PreparedStatement statementObject=null;
        boolean flagForDelete=false;

        String query="DELETE FROM customer where customer_id="+id;

        try
        {
            connection =DBConnection.getConnection();
            statementObject = connection.prepareStatement(query);
            flagForDelete= statementObject.execute();


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try
            {

                if(statementObject!=null)
                    statementObject.close();

                if(connection!=null)
                    connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return flagForDelete;
    }

}
