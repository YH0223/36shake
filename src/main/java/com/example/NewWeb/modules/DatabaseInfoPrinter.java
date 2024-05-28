package com.example.NewWeb.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseInfoPrinter {

    @Autowired
    private DataSource dataSource;

    public void printDatabaseInfo() {
        try {
            Connection connection = dataSource.getConnection();
            String url = connection.getMetaData().getURL();
            String username = connection.getMetaData().getUserName();
            String databaseProductName = connection.getMetaData().getDatabaseProductName();

            System.out.println("Connected to database:");
            System.out.println("URL: " + url);
            System.out.println("Username: " + username);
            System.out.println("Database Product Name: " + databaseProductName);

            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving database connection information: " + e.getMessage());
        }
    }
}