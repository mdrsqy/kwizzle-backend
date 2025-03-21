package com.kwizzle.service;

import com.kwizzle.model.Quiz;
import com.kwizzle.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Optional<Quiz> updateQuiz(Long id, Quiz quiz) {
        return quizRepository.findById(id).map(existingQuiz -> {
            existingQuiz.setTitle(quiz.getTitle());
            existingQuiz.setSummary(quiz.getSummary());
            existingQuiz.setScore(quiz.getScore());
            existingQuiz.setTimeLimit(quiz.getTimeLimit());
            existingQuiz.setJoinCode(quiz.getJoinCode());
            existingQuiz.setUpdatedAt(quiz.getUpdatedAt());
            existingQuiz.setPrivate(quiz.isPrivate());
            return quizRepository.save(existingQuiz);
        });
    }

    public boolean deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}