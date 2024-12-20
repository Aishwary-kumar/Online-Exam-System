package com.exam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exam.model.User;

public class UserRepository {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/online_exam_db";
    @SuppressWarnings("FieldMayBeFinal")
    private String jdbcUsername = "root"; // replace with your db username
    @SuppressWarnings("FieldMayBeFinal")
    private String jdbcPassword = "password"; // replace with your db password

    public User findUserByUsername(String username) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return user;
    }
}