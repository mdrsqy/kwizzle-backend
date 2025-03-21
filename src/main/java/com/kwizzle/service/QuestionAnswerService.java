package com.kwizzle.service;

import com.kwizzle.model.QuestionAnswer;
import com.kwizzle.repository.QuestionAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;

    public QuestionAnswerService(QuestionAnswerRepository quizAnswerRepository) {
        this.questionAnswerRepository = quizAnswerRepository;
    }

    public List<QuestionAnswer> findAll() {
        return questionAnswerRepository.findAll();
    }

    public Optional<QuestionAnswer> findById(Long id) {
        return questionAnswerRepository.findById(id);
    }

    public QuestionAnswer save(QuestionAnswer quizAnswer) {
        return questionAnswerRepository.save(quizAnswer);
    }

    public void deleteById(Long id) {
        questionAnswerRepository.deleteById(id);
    }
}