package com.example.NewWeb.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DatabaseInfoPrinter databaseInfoPrinter;

    public void checkDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database connection is successful.");
            databaseInfoPrinter.printDatabaseInfo();
        } catch (Exception e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}