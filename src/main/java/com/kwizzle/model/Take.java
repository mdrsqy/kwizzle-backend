package com.kwizzle.model;

import com.kwizzle.enums.TakeStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "take")
public class Take {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_id", nullable = false)
    private TakeStatus status;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private int score;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int duration;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    public Take() {
        this.createdAt = LocalDateTime.now();
    }

    public Take(User user, Quiz quiz, TakeStatus status, int score, int duration, LocalDateTime startedAt, LocalDateTime finishedAt) {
        this.user = user;
        this.quiz = quiz;
        this.status = status;
        this.score = score;
        this.duration = duration;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public TakeStatus getStatus() {
        return status;
    }

    public void setStatus(TakeStatus status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}