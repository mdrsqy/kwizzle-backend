package com.kwizzle.controller;

import com.kwizzle.model.Quiz;
import com.kwizzle.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<?> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            return ResponseEntity.ok(quizzes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal mengambil data kuis.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable Long id) {
        try {
            Optional<Quiz> quiz = quizService.getQuizById(id);
            return quiz.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Kuis dengan ID " + id + " tidak ditemukan."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal mengambil kuis.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz savedQuiz = quizService.saveQuiz(quiz);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Kuis berhasil dibuat dengan ID: " + savedQuiz.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Gagal membuat kuis, pastikan semua data valid.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        try {
            Optional<Quiz> updatedQuiz = quizService.updateQuiz(id, quiz);
            return updatedQuiz.map(q -> ResponseEntity.ok("Kuis dengan ID " + id + " berhasil diperbarui."))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Kuis dengan ID " + id + " tidak ditemukan."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Gagal memperbarui kuis, pastikan semua data valid.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        try {
            if (quizService.deleteQuiz(id)) {
                return ResponseEntity.ok("Kuis dengan ID " + id + " berhasil dihapus.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Kuis dengan ID " + id + " tidak ditemukan.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal menghapus kuis.");
        }
    }
}