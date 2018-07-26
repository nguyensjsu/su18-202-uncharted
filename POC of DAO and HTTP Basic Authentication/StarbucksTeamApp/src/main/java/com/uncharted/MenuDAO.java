package com.uncharted;

import java.util.ArrayList;

public interface MenuDAO {

 //GET
    MenuBO getMenuItems(int item);
    ArrayList<MenuBO> getAllMenuItems();

    //POST
    public boolean addNewItem(MenuBO bo);
    public boolean updateItem(MenuBO bo);

    //DELETE
    public boolean deleteItem(int item);
}
