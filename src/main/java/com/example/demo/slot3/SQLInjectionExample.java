package com.example.demo.slot3;

import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) {
        String userInput = "' OR '1'='1";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass")) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userInput);

            System.out.println("Executing query safely with parameter binding:");
            System.out.println(stmt);

            // Uncomment if you want to execute the query

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
