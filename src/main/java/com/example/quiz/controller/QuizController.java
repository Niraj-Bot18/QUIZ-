package com.example.quiz.controller;

import com.example.quiz.entity.Question;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // 1. Get all questions
    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }

    // 2. Add question
    @PostMapping("/questions")
    public Question addQuestion(@RequestBody Question question) {
        return quizService.addQuestion(question);
    }

    // 3. Delete question
    @DeleteMapping("/questions/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        quizService.deleteQuestion(id);
        return "Question deleted successfully";
    }

    // 4. Generate Quiz
    @GetMapping("/quiz/generate")
    public List<Question> generateQuiz(@RequestParam(defaultValue = "ALL") String category,
                                      @RequestParam(defaultValue = "5") int count) {
        return quizService.generateQuiz(category, count);
    }

    // 5. Submit Quiz & Return Score
    @PostMapping("/quiz/submit")
    public int submitQuiz(@RequestBody Map<Long, String> answers) {
        return quizService.calculateScore(answers);
    }
}
