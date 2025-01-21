package com.exam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exam.model.User;

public class UserRepository {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/online_exam_db";
    private String jdbcUsername = "root"; // replace with your db username
    private String jdbcPassword = "password"; // replace with your db password

    public User findUserByUsername(String username) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
                );
                user.setId(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return user;
    }

    public void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
}
