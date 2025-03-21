package com.kwizzle.controller;

import com.kwizzle.model.QuizQuestion;
import com.kwizzle.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz-questions")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @Autowired
    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

    @GetMapping
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionService.getAllQuizQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestion> getQuizQuestionById(@PathVariable Long id) {
        Optional<QuizQuestion> quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return quizQuestion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuizQuestion> createOrUpdateQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        QuizQuestion savedQuizQuestion = quizQuestionService.saveQuizQuestion(quizQuestion);
        return ResponseEntity.ok(savedQuizQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizQuestion(@PathVariable Long id) {
        quizQuestionService.deleteQuizQuestion(id);
        return ResponseEntity.noContent().build();
    }
}