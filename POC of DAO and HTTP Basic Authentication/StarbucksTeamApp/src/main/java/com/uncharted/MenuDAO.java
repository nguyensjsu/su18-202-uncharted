package com.uncharted;

import java.util.ArrayList;

public interface MenuDAO {

 //GET
    MenuBO getMenuItems(int item);
    ArrayList<MenuBO> getAllMenuItems();

    //POST
    public void addNewItem(MenuBO bo);
    public void updateItem(MenuBO bo);

    //DELETE
    public void deleteItem(int item);
}
