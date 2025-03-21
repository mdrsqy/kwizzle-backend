package com.kwizzle.service;

import com.kwizzle.model.QuizAnswer;
import com.kwizzle.repository.QuizAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;

    public QuizAnswerService(QuizAnswerRepository quizAnswerRepository) {
        this.quizAnswerRepository = quizAnswerRepository;
    }

    public List<QuizAnswer> findAll() {
        return quizAnswerRepository.findAll();
    }

    public Optional<QuizAnswer> findById(Long id) {
        return quizAnswerRepository.findById(id);
    }

    public QuizAnswer save(QuizAnswer quizAnswer) {
        return quizAnswerRepository.save(quizAnswer);
    }

    public void deleteById(Long id) {
        quizAnswerRepository.deleteById(id);
    }
}