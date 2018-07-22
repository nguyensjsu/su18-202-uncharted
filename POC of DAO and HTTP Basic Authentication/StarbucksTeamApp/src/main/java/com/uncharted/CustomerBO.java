package com.uncharted;

public class CustomerBO
{
  private int customer_id;
  private String customer_name;
  private String customer_birth;
  private String customer_gender;
  private String customer_user_name;
  private String customer_password;

  public CustomerBO()
  {

  }

  public CustomerBO(int customer_id,String customer_name,String customer_birth,String customer_gender,String customer_user_name,String customer_password)
  {
      this.customer_id=customer_id;
      this.customer_name=customer_name;
      this.customer_birth=customer_birth;
      this.customer_gender=customer_gender;
      this.customer_user_name=customer_user_name;
      this.customer_password=customer_password;

  }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_birth() {
        return customer_birth;
    }

    public void setCustomer_birth(String customer_birth) {
        this.customer_birth = customer_birth;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_user_name() {
        return customer_user_name;
    }

    public void setCustomer_user_name(String customer_user_name) {
        this.customer_user_name = customer_user_name;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }
}
