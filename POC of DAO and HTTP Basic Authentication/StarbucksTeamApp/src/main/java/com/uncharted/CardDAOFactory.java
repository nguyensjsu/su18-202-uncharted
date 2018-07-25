package com.uncharted;

public class CardDAOFactory {
    public static CardDAO getInstance(){
        return new CardDAOImpl();
    }
}
