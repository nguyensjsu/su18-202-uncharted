package com.uncharted;

import java.util.ArrayList;

public class MenuService {

    public MenuBO getMenuItems(int item)
    {
        MenuDAO menuDAO = MenuDAOFactory.getInstance();
        return menuDAO.getMenuItems(item);
    }

    public ArrayList<MenuBO> getAllMenuItems()
    {
        MenuDAO menuDAO = MenuDAOFactory.getInstance();
        return menuDAO.getAllMenuItems();
    }
}
