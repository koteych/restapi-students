package org.restapi;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.restapi.Student;

public class StudentService {
    private static final String DB_URL = "jdbc:postgresql://sql_server:5432/improvised_university";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mysecretpassword";


    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM students";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String middleName = resultSet.getString("middle_name");
                    Date dateOfBirth = resultSet.getDate("date_of_birth");
                    String group = resultSet.getString("group_name");

                    Student student = new Student(id, firstName, lastName, middleName, group, "");

                    students.add(student);

                    System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + " " + middleName + ", Date of Birth: " + dateOfBirth + ", Group: " + group);
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return students;
    }

    public void addStudent(String name) {
        String firstName = "John";
        String lastName = "Doe";
        String middleName = "Smith";
        Date dateOfBirth = Date.valueOf("2000-01-15");
        String group = "A";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO students (first_name, last_name, middle_name, date_of_birth, group_name) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, middleName);
                preparedStatement.setDate(4, dateOfBirth);
                preparedStatement.setString(5, group);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student added successfully");
                } else {
                    System.out.println("Failed to add student");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        int studentIdToDelete = id;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "DELETE FROM students WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentIdToDelete);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student with ID " + studentIdToDelete + " deleted successfully");
                } else {
                    System.out.println("No student found with ID " + studentIdToDelete);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}