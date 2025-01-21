package com.exam.service;

import java.util.HashMap;
import java.util.Map;

public class ExamService {

    // A simple in-memory representation of questions.
    // In a real application, you would retrieve these from a database.
    private static final Map<String, String> questions = new HashMap<>();

    static {
questions.put("What is the capital of France?", "Paris");
questions.put("What is 2 + 2?", "4");
        // Add more questions as needed.
    }

    public Map<String, String> getQuestions() {
        return questions;
    }

    public int evaluateExam(Map<String, String> answers) {
        int score = 0;

        for (Map.Entry<String, String> entry : answers.entrySet()) {
            String question = entry.getKey();
            String answer = entry.getValue();

            // Check if the provided answer is correct
            if (questions.get(question).equals(answer)) {
                score++;
            }
        }

        return score; // returning the total score
    }
}