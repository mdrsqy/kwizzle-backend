package com.kwizzle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_answer")
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_correct", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isCorrect;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public QuizAnswer() {
        this.createdAt = LocalDateTime.now();
    }

    public QuizAnswer(Long id, QuizQuestion question, String content, boolean isCorrect) {
        this.id = id;
        this.question = question;
        this.content = content;
        this.isCorrect = isCorrect;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}