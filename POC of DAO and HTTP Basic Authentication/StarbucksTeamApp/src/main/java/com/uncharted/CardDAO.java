package com.uncharted;

public interface CardDAO {

    CardBO getCard(int id);
    public boolean addCard(CardBO cardBO);
    boolean updateCardBalance(int cardid, double amount);
}
