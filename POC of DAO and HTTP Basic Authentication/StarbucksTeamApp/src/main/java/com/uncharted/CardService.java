package com.uncharted;

public class CardService {

    public CardBO getCardByCust(int id)
    {
        CardDAO cardDAO=CardDAOFactory.getInstance();

        return cardDAO.getCard(id);
    }
    public boolean addCard(CardBO cardBO)
    {
        CardDAO cardDAO=CardDAOFactory.getInstance();
        return cardDAO.addCard(cardBO);
    }

    public boolean updateCardBalance(int cardid, double balance) {
        CardDAO cardDAO=CardDAOFactory.getInstance();
        return cardDAO.updateCardBalance(cardid,balance);
    }
}
