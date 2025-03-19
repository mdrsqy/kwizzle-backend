package com.kwizzle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "take_answer")
public class TakeAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "take_id", nullable = false)
    private Take take;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private QuizAnswer answer;

    @Column(name = "answered_at", updatable = false)
    private LocalDateTime answeredAt = LocalDateTime.now();

    public TakeAnswer() {
    }

    public TakeAnswer(Take take, QuizQuestion question, QuizAnswer answer, LocalDateTime answeredAt) {
        this.take = take;
        this.question = question;
        this.answer = answer;
        this.answeredAt = answeredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Take getTake() {
        return take;
    }

    public void setTake(Take take) {
        this.take = take;
    }

    public QuizQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }

    public QuizAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(QuizAnswer answer) {
        this.answer = answer;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }
}