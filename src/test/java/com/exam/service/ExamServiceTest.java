import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.exam.service.ExamService;

import java.util.HashMap;
import java.util.Map;

public class ExamServiceTest {
    private ExamService examService;

    @BeforeEach
    public void setUp() {
        examService = new ExamService();
    }

    @Test
    public void testEvaluateExam() {
        Map<String, String> answers = new HashMap<>();
        answers.put("What is the capital of France?", "Paris");
        answers.put("What is 2 + 2?", "4");

        int score = examService.evaluateExam(answers);
        assertEquals(2, score); // Assuming both answers are correct
    }
}
