package com.uncharted;

public class CardBO {

    private int card_id;
    private String card_code;
    private double card_balance;
    private int customer_id;

    public CardBO() {

    }

    public CardBO(int card_id, String card_code, double card_balance, int customer_id)
    {
        this.card_id=card_id;
        this.card_code=card_code;
        this.card_balance=card_balance;
        this.customer_id=customer_id;
    }


    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }

    public double getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(double card_balance) {
        this.card_balance = card_balance;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }



}
