System - Java Project Overview The Online Exam System is a Java-based application designed to allow users to take online exams in a secure and user-friendly environment. The system enables administrators to manage exams, create questions, and evaluate user performance, while students can take exams, view their results, and track their progress.

Features User authentication (Student and Admin roles). Admin panel for creating and managing exams and questions. Timed exam sessions for students. Automatic grading and result generation. Exam history and performance tracking for students. Secure login and session management. Requirements To run this project, the following tools and technologies are required:

Java Development Kit (JDK): Version 8 or higher. MySQL Database: For storing user and exam data. Apache Maven: For managing project dependencies. IntelliJ IDEA or any other preferred Java IDE. MySQL Workbench or similar for managing the database. Database Setup Create the Database: Open MySQL Workbench (or your MySQL client) and create the database for the project.

sql Copy code CREATE DATABASE online_exam_system; Create Tables:

Run the following SQL script to create the necessary tables in your database.

sql Copy code CREATE TABLE users ( id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(100) NOT NULL, password VARCHAR(255) NOT NULL, role ENUM('admin', 'student') NOT NULL );

CREATE TABLE exams ( id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, duration INT NOT NULL, -- duration in minutes created_by INT, -- references the admin who created the exam FOREIGN KEY (created_by) REFERENCES users(id) );

CREATE TABLE questions ( id INT AUTO_INCREMENT PRIMARY KEY, exam_id INT, question_text TEXT NOT NULL, option_a VARCHAR(255) NOT NULL, option_b VARCHAR(255) NOT NULL, option_c VARCHAR(255), option_d VARCHAR(255), correct_option CHAR(1), -- 'A', 'B', 'C', or 'D' FOREIGN KEY (exam_id) REFERENCES exams(id) );

CREATE TABLE exam_attempts ( id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, exam_id INT, attempt_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, score INT, FOREIGN KEY (user_id) REFERENCES users(id), FOREIGN KEY (exam_id) REFERENCES exams(id) ); Configure Database Connection: Update the DB_URL, USER, and PASSWORD constants in your Java code (usually in a DBConnector class or similar) to match your MySQL database setup.

Example in Java:

java Copy code public class DBConnector { public static final String DB_URL = "jdbc:mysql://localhost:3306/online_exam_system"; public static final String USER = "root"; public static final String PASSWORD = "your_password"; } Project Setup Clone the Repository:

If you have the repository URL, clone it using Git:

bash Copy code git clone cd online-exam-system Navigate to Project Directory: Ensure you're in the project directory where the pom.xml file is located (if using Maven).

Install Dependencies:

If you're using Maven to manage dependencies, install them by running:

bash Copy code mvn install Running the Project

Open IntelliJ IDEA: Open the project in IntelliJ IDEA or any other Java IDE.

Run the Main Class: Locate the Main.java or an appropriate entry point class in your project, typically found in the src/main/java/com/onlineexam directory.

Right-click on the class and select Run.

Example:

java Copy code public class Main { public static void main(String[] args) { // Initialize the system (Start the login interface) System.out.println("Welcome to the Online Exam System"); // Add logic for user login and exam functionality } } 3. Testing Functionality: Admin Role: Admin can create exams, add questions, and manage users. Student Role: Students can login, view available exams, take the exam, and view their results. Example for creating an exam:

java Copy code // Example code for adding an exam by admin public void createExam(String title, int duration) { String sql = "INSERT INTO exams (title, duration, created_by) VALUES (?, ?, ?)"; try (Connection conn = DBConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) { stmt.setString(1, title); stmt.setInt(2, duration); stmt.setInt(3, adminId); // Admin's user id stmt.executeUpdate(); System.out.println("Exam created successfully!"); } catch (SQLException e) { e.printStackTrace(); } } 4. Sample Student Exam Flow: Students can see the list of available exams. Select an exam and start the timer. Answer the multiple-choice questions. Submit the exam, and the score is automatically calculated. Project Structure Here's a brief overview of the directory structure:

bash Copy code /online-exam-system │ ├── /src │ ├── /com │ │ └── /onlineexam │ │ ├── Main.java # Entry point for the application │ │ ├── ExamManager.java # Logic for handling exams │ │ ├── UserManager.java # Logic for user management │ │ └── DBConnector.java # Database connection management │ ├── /resources │ └── /config │ └── database.properties # Database configuration file │ └── /pom.xml # Maven configuration file Notes Ensure MySQL Server is Running: Before running the project, make sure your MySQL server is up and accessible with the provided credentials. Maven: The project uses Maven for dependency management. If using IntelliJ IDEA, Maven should automatically handle dependencies when you open the project. License This project is licensed under the MIT License. See the LICENSE file for more details.

Additional Features (Optional) You could extend the system with features such as:

Question Bank: Store questions in a database and allow admins to randomly select questions for each exam. Timer: Implement a countdown timer for each exam. Report Generation: Generate detailed exam reports for students and admins. Web Interface: Implement a web-based frontend using technologies like Spring Boot.
