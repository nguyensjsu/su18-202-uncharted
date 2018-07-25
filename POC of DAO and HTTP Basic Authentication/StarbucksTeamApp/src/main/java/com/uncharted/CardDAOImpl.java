package com.uncharted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CardDAOImpl implements CardDAO {

    public CardBO getCard(int cust_id) {
        CardBO card = new CardBO();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from Card where customer_id=" + cust_id;

        try {
            connection = DBConnection.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                card.setCard_id(rs.getInt("card_id"));
                card.setCard_code(rs.getString("card_code"));
                card.setCard_balance(rs.getDouble("card_balance"));
                card.setCustomer_id(rs.getInt("customer_id"));

            } else {
                System.out.println("Empty value in result set");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (st != null)
                    st.close();

                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return card;
    }

    public boolean addCard(CardBO cardBO) {

        Connection connection = null;
        PreparedStatement statementObject = null;
        boolean flagForRegistration = false;

        CardBO bo=getCard(cardBO.getCustomer_id());

        String query="";
        if(bo.getCard_code()==null)
            query = "INSERT INTO Card(card_id,card_code,card_balance,customer_id) VALUES (?,?,?,?)";
        else
            query = "UPDATE Card set card_id=?, card_code=?,card_balance=? where customer_id=?";
        try {
            connection = DBConnection.getConnection();
            statementObject = connection.prepareStatement(query);
            System.out.println("Query is:" + query);
            statementObject = connection.prepareStatement(query);
            statementObject.setInt(1, cardBO.getCard_id());
            statementObject.setString(2, cardBO.getCard_code());
            statementObject.setDouble(3, cardBO.getCard_balance());
            statementObject.setInt(4, cardBO.getCustomer_id());


            flagForRegistration = statementObject.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (statementObject != null)
                    statementObject.close();

                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return !flagForRegistration;

    }

    @Override
    public boolean updateCardBalance(int cardid, double amount) {
        Connection connection = null;
        PreparedStatement statementObject = null;
        boolean flagForRegistration = false;

        String query = "UPDATE Card set card_balance=? where card_id=?";

        try {
            connection = DBConnection.getConnection();
            statementObject = connection.prepareStatement(query);
            System.out.println("Query is:" + query);
            statementObject = connection.prepareStatement(query);
            statementObject.setDouble(1, amount);
            statementObject.setInt(2, cardid);


            flagForRegistration = statementObject.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (statementObject != null)
                    statementObject.close();

                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return !flagForRegistration;
    }
}

