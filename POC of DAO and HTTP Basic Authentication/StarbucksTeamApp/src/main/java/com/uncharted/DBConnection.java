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
            con=DriverManager.getConnection("jdbc:mysql:/");
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
