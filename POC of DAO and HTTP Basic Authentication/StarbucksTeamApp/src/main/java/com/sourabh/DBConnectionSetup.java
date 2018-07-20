package com.sourabh;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionSetup {
    private static Connection con=null;

    private static void getConnection() throws Exception
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://<dburl>:<port>/<dbname>";
        String username = "<un>";
        String password = "<pwd>";
        Class.forName(driver);

        con = DriverManager.getConnection(url, username, password);
        System.out.println("connected");
    }

    public static void closeConnection() throws Exception
    {
        try {
            if (con != null) {
                con.close();
                System.out.println("disconnected");
            }
        }
        catch (Exception ex)
        {
            System.out.print("here");
            throw ex;
        }
    }

    public static ResultSet getSelectQueryResult(String query) throws Exception
    {
        getConnection();
        if(con!=null) {
            Statement stmt = con.createStatement();
            if (stmt != null)
                return stmt.executeQuery(query);
        }
        return null;
    }

    public static boolean getInsertUpdateDeleteQueryResult(String query) throws Exception
    {
        getConnection();
        if(con!=null) {
            Statement stmt = con.createStatement();
            if (stmt != null)
                return stmt.execute(query);
        }
        return false;
    }

    public static JSONArray convertToJSON(ResultSet resultSet) throws Exception {
        JSONArray resultList = new JSONArray();
        ResultSetMetaData rsmd= resultSet.getMetaData();
        int colCnt= rsmd.getColumnCount();
        List<String> colNames= new ArrayList<String>();
        for(int i=1;i<=colCnt;i++)
        {
            colNames.add(rsmd.getColumnName(i).toUpperCase());
        }

        while (resultSet.next()) {
            JSONObject obj = new JSONObject();
            for(int i=1;i<=colCnt;i++)
            {
                String key = colNames.get(i-1);
                String value = resultSet.getString(i);
                obj.put(key,value);
            }
            resultList.put(obj);
        }
        resultSet.close();
        return resultList;
    }
}
