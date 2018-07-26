package com.uncharted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAOImpl implements MenuDAO {
    public MenuBO getMenuItems(int item) {
        return null;
    }

    public ArrayList<MenuBO> getAllMenuItems() {

        ArrayList<MenuBO> menuList=new ArrayList<MenuBO>();

        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;

        String query="select * from Menu";

        try
        {
            connection =DBConnection.getConnection();
            st=connection.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next())
            {
                MenuBO menu=new MenuBO();
                menu.setItemID(rs.getInt("item_id"));
                menu.setItemName(rs.getString("item_name"));
                menu.setItemPrice(rs.getDouble("item_price"));
                menu.setStoreID(rs.getInt("store_id"));
                menu.setCateogory(rs.getString("category"));
                menuList.add(menu);
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
        return menuList;
    }

    public boolean addNewItem(MenuBO bo) {

        Connection connection=null;
        PreparedStatement statementObject=null;
        boolean flagForRegistration=false;

        String query="INSERT INTO Menu(item_name,item_price,store_id,category) VALUES (?,?,?,?)";

        try
        {
            connection =DBConnection.getConnection();
            statementObject=connection.prepareStatement(query);
            System.out.println("Query is:"+query);
            statementObject = connection.prepareStatement(query);

            statementObject.setString(1, bo.getItemName());
            statementObject.setDouble(2, bo.getItemPrice());
            statementObject.setInt(3,bo.getStoreID());
            statementObject.setString(4, bo.getCateogory());
            statementObject.execute();

            int count=statementObject.getUpdateCount();

            if(count==1)
                flagForRegistration=true;

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

    public boolean updateItem(MenuBO bo)
        {

            Connection connection=null;
            PreparedStatement statementObject=null;
            boolean flagForUpdate=false;

            String query="update menu SET item_name=? ,item_price=?,store_id=?,category=? where item_id=?";

            try
            {

                connection =DBConnection.getConnection();
                statementObject = connection.prepareStatement(query);
                statementObject.setString(1, bo.getItemName());
                statementObject.setDouble(2, bo.getItemPrice());
                statementObject.setInt(3, bo.getStoreID());
                statementObject.setString(4,bo.getCateogory());
                statementObject.setInt(5, bo.getItemID());

                statementObject.execute();

                int count=statementObject.getUpdateCount();

                if(count==1)
                    flagForUpdate=true;



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

    public boolean deleteItem(int item) {
        return false;
    }

}
