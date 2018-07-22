package com.uncharted;

import java.sql.*;


public class DBConnection
{

    public static Connection getConnection()
    {
        Connection con=null;
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://uncharted4.c1bk7x4hj9tm.us-west-1.rds.amazonaws.com:3306/starbucks","Uncharted4","Uncharted4");
            System.out.println("The connection is:"+con);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con)
    {
        try
        {
            if(con!=null)
                con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }

}
