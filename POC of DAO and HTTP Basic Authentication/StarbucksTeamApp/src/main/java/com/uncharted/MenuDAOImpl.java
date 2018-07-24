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

    public void addNewItem(MenuBO bo) {

    }

    public void updateItem(MenuBO bo) {

    }

    public void deleteItem(int item) {

    }
}
