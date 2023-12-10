package com.flipkart.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;
    private DBConnection() {}

    public static Connection getConnection() throws SQLException {

        if (null == connection) {
            final FileReader fileReader;
            try {
                fileReader = new FileReader("db_credentials.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found");
            }
            final Properties properties = new Properties();
            try {
                properties.load(fileReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"), properties.getProperty("password"));
        }

        return connection;
    }
}
