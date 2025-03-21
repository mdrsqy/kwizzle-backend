package com.kwizzle.controller;

import com.kwizzle.model.QuestionAnswer;
import com.kwizzle.service.QuestionAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question-answers")
public class QuestionAnswerController {

    private final QuestionAnswerService questionAnswerService;

    public QuestionAnswerController(QuestionAnswerService quizAnswerService) {
        this.questionAnswerService = quizAnswerService;
    }

    @GetMapping
    public List<QuestionAnswer> getAllQuizAnswers() {
        return questionAnswerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionAnswer> getQuizAnswerById(@PathVariable Long id) {
        return questionAnswerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuestionAnswer createQuizAnswer(@RequestBody QuestionAnswer quizAnswer) {
        quizAnswer.setCreatedAt(java.time.LocalDateTime.now());
        return questionAnswerService.save(quizAnswer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionAnswer> updateQuizAnswer(@PathVariable Long id, @RequestBody QuestionAnswer updatedAnswer) {
        return questionAnswerService.findById(id)
                .map(existing -> {
                    existing.setQuestion(updatedAnswer.getQuestion());
                    existing.setContent(updatedAnswer.getContent());
                    existing.setCorrect(updatedAnswer.isCorrect());
                    return ResponseEntity.ok(questionAnswerService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuizAnswer(@PathVariable Long id) {
        return questionAnswerService.findById(id)
                .map(answer -> {
                    questionAnswerService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}