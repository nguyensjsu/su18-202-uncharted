package com.uncharted;

public class MenuDAOFactory {

    public static MenuDAO getInstance()
    {
        return (MenuDAO) new MenuDAOImpl();
    }
}
