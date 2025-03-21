package com.kwizzle.controller;

import com.kwizzle.model.QuizAnswer;
import com.kwizzle.service.QuizAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-answers")
public class QuizAnswerController {

    private final QuizAnswerService quizAnswerService;

    public QuizAnswerController(QuizAnswerService quizAnswerService) {
        this.quizAnswerService = quizAnswerService;
    }

    @GetMapping
    public List<QuizAnswer> getAllQuizAnswers() {
        return quizAnswerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAnswer> getQuizAnswerById(@PathVariable Long id) {
        return quizAnswerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuizAnswer createQuizAnswer(@RequestBody QuizAnswer quizAnswer) {
        quizAnswer.setCreatedAt(java.time.LocalDateTime.now());
        return quizAnswerService.save(quizAnswer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizAnswer> updateQuizAnswer(@PathVariable Long id, @RequestBody QuizAnswer updatedAnswer) {
        return quizAnswerService.findById(id)
                .map(existing -> {
                    existing.setQuestion(updatedAnswer.getQuestion());
                    existing.setContent(updatedAnswer.getContent());
                    existing.setCorrect(updatedAnswer.isCorrect());
                    return ResponseEntity.ok(quizAnswerService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuizAnswer(@PathVariable Long id) {
        return quizAnswerService.findById(id)
                .map(answer -> {
                    quizAnswerService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}