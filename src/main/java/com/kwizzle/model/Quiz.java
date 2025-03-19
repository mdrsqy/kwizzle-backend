package com.kwizzle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hostId;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false, length = 75)
    private String title;

    @Column(length = 100)
    private String metaTitle;

    @Column(unique = true, nullable = false, length = 100)
    private String slug;

    @Lob
    private String summary;

    @Column(nullable = false)
    private Long typeId;

    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    private int score;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean published;

    private Integer timeLimit;

    @Column(unique = true, length = 20)
    private String joinCode;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;

    // Constructor tanpa parameter
    public Quiz() {}

    // Constructor dengan parameter lengkap
    public Quiz(Long id, Long hostId, Long categoryId, String title, String metaTitle, String slug, String summary,
                Long typeId, int score, boolean published, Integer timeLimit, String joinCode,
                LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime publishedAt,
                LocalDateTime startsAt, LocalDateTime endsAt) {
        this.id = id;
        this.hostId = hostId;
        this.categoryId = categoryId;
        this.title = title;
        this.metaTitle = metaTitle;
        this.slug = slug;
        this.summary = summary;
        this.typeId = typeId;
        this.score = score;
        this.published = published;
        this.timeLimit = timeLimit;
        this.joinCode = joinCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    // Getter dan Setter untuk setiap atribut
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }
}