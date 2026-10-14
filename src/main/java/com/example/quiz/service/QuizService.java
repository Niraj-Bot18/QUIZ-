package com.example.quiz.service;

import com.example.quiz.entity.Question;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    // Seed sample questions automatically if database is empty
    @PostConstruct
    public void initQuestions() {
        if (questionRepository.count() == 0) {
            questionRepository.save(new Question("Which OOP concept binds data and methods together?", "Inheritance", "Encapsulation", "Polymorphism", "Abstraction", "Encapsulation", "Java"));
            questionRepository.save(new Question("Which keyword is used to inherit a class in Java?", "implements", "extends", "inherits", "instanceof", "extends", "Java"));
            questionRepository.save(new Question("Which data structure works on LIFO principle?", "Queue", "Stack", "Array", "Linked List", "Stack", "Java"));
            questionRepository.save(new Question("Which collection in Java does not allow duplicate values?", "List", "ArrayList", "Set", "Vector", "Set", "Java"));
            questionRepository.save(new Question("What is the default value of boolean in Java?", "true", "false", "0", "null", "false", "Java"));
        }
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Add a new question
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Delete a question
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    // Generate Quiz: Randomly picks N questions using Collections.shuffle
    public List<Question> generateQuiz(String category, int count) {
        List<Question> list = category.equalsIgnoreCase("ALL")
                ? questionRepository.findAll()
                : questionRepository.findByCategory(category);

        // Second year concept: Collections Framework (shuffle)
        Collections.shuffle(list);
        int limit = Math.min(count, list.size());
        return new ArrayList<>(list.subList(0, limit));
    }

    // Calculate score: Compares student answers map {questionId: selectedAnswer}
    public int calculateScore(Map<Long, String> answers) {
        int score = 0;
        for (Map.Entry<Long, String> entry : answers.entrySet()) {
            Question q = questionRepository.findById(entry.getKey()).orElse(null);
            if (q != null && q.getRightAnswer().equalsIgnoreCase(entry.getValue())) {
                score++;
            }
        }
        return score;
    }
}
