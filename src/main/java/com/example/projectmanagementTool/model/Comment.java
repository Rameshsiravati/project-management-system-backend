package com.example.projectmanagementTool.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private LocalDateTime createdAt;

    public Comment() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Issue getIssue() {
        return issue;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
