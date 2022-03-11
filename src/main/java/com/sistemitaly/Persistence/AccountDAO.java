package com.sistemitaly.Persistence;

import com.sistemitaly.Bean.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";

    public void insertTransaction(Transaction transaction) {

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql =  "INSERT INTO TRANSACTION VALUES (" +
                    transaction.transactionId +
                    transaction.operationId +
                    transaction.accountingDate +
                    transaction.valueDate +
                    transaction.type.enumeration +
                    transaction.amount +
                    transaction.currency +
                    transaction.description +
                    "); ";

            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}