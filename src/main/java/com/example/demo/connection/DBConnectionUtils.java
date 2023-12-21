package com.example.demo.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectionUtils {

    public static Connection getFirstConnection() {
        try {
            Connection connection = DriverManager.getConnection(FirstConnection.URL, FirstConnection.USERNAME, FirstConnection.PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Connection getSecondConnection() {
        try {
            Connection connection = DriverManager.getConnection(SecondConnection.URL, SecondConnection.USERNAME, SecondConnection.PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
