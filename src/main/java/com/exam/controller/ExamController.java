package com.exam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model.User;
import com.exam.repository.UserRepository;
import com.exam.service.ExamService;

public class ExamController extends HttpServlet {
    private UserRepository userRepository;
    private ExamService examService;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
        examService = new ExamService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userRepository.findUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            response.sendRedirect("login.html"); // Redirect back to login
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("quiz.html"); // Redirect to the quiz page
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String path = request.getRequestURI();
if (path.equals("/exam")) { // Adjust this condition based on your URL mapping
    RequestDispatcher dispatcher = request.getRequestDispatcher("webview/index.jsp");
    dispatcher.forward(request, response);
} else {
    // Display the quiz page
    RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.html");
    dispatcher.forward(request, response);
}
    }

    protected void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login page
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request, response);
    }

    protected void doPostSubmitQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> answers = new HashMap<>();

        // Assuming questions are known and we know the parameter names in the quiz form.
        answers.put("What is the capital of France?", request.getParameter("question1"));
        answers.put("What is 2 + 2?", request.getParameter("question2"));

        int score = examService.evaluateExam(answers); // Evaluate the exam
        request.setAttribute("score", score); // Set score to request attribute
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp"); // Redirect to results page
        dispatcher.forward(request, response);
    }
}
