package com.kwizzle.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_answer")
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect = false;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Constructor kosong
    public QuizAnswer() {
    }

    // Constructor dengan parameter
    public QuizAnswer(QuizQuestion question, String content, boolean isCorrect, Timestamp createdAt) {
        this.question = question;
        this.content = content;
        this.isCorrect = isCorrect;
        this.createdAt = createdAt;
    }

    // Getter dan Setter
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // toString untuk debugging
    @Override
    public String toString() {
        return "QuizAnswer{" +
                "id=" + id +
                ", question=" + question +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                ", createdAt=" + createdAt +
                '}';
    }
}