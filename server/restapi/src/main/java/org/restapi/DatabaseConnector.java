package org.restapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DatabaseConnector{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mysecretpassword";


    void someMethod() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the PostgreSQL server successfully.");

            String sql = "CREATE TABLE IF NOT EXISTS employees " +
                    "(id SERIAL PRIMARY KEY, " +
                    " name VARCHAR(255), " +
                    " age INT)";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement statement = conn.createStatement()) {
                // create a new table
                statement.execute(sql);
                System.out.println("Table created successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            // Create and execute a sample query
            String query = "SELECT * FROM employees WHERE department = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "IT");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println("Employee ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name"));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute the query.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}