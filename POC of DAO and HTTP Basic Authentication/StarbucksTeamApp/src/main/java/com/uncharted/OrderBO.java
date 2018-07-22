package com.uncharted;

public class OrderBO {
    private String order_details, order_pay_datetime;
    private double order_total;
    private int order_id, customer_id;

    OrderBO()
    {
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public String getOrder_pay_datetime() {
        return order_pay_datetime;
    }

    public void setOrder_pay_datetime(String order_pay_datetime) {
        this.order_pay_datetime = order_pay_datetime;
    }

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
