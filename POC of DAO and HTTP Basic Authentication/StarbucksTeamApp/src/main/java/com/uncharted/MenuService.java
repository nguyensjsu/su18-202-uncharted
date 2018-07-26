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

    public boolean additems(MenuBO menuBO)
    {
        MenuDAO menuDAO=MenuDAOFactory.getInstance();
        return menuDAO.addNewItem(menuBO);
    }

    public boolean updateItem(MenuBO menuBO)
    {
        MenuDAO menuDAO=MenuDAOFactory.getInstance();
        return menuDAO.updateItem(menuBO);
    }

    public boolean deleteitem(int id)
    {
        MenuDAO menuDAO=MenuDAOFactory.getInstance();
        return menuDAO.deleteItem(id);
    }
}
