package com.homework.dynamic.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bob
 */
public class ConnectionTest {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String userName = "root";
    String password = "root";
    String url = "jdbc:mysql://127.0.0.1:3306/test";
    Connection connection = DriverManager.getConnection(
        url,
        userName,
        password
    );

    System.out.println(connection);
  }

}
