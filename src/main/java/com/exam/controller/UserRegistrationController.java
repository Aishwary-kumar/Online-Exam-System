package com.exam.controller;

import java.io.IOException;

import javax.servlet.ServletException; // Ensure this import is present
import javax.servlet.http.HttpServlet; // Ensure this import is present
import javax.servlet.http.HttpServletRequest; // Ensure this import is present
import javax.servlet.http.HttpServletResponse; // Ensure this import is present

import com.exam.model.User;
import com.exam.repository.UserRepository;

public class UserRegistrationController extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String username = request.getParameter("username"); // Example: get username from request
User user = userRepository.findUserByUsername(username); // Fetch user data

if (user != null) {
    request.setAttribute("user", user); // Set user data as request attribute
    request.getRequestDispatcher("webview/userData.jsp").forward(request, response); // Forward to userData.jsp
} else {
    // Display the registration form if user not found
    request.getRequestDispatcher("register.html").forward(request, response);
}
        request.getRequestDispatcher("register.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle user registration
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "user"; // Default role

        User newUser = new User(username, password, role);
        userRepository.saveUser(newUser); // Save the user to the repository

        response.sendRedirect("login.html"); // Redirect to login page after registration
    }
}
