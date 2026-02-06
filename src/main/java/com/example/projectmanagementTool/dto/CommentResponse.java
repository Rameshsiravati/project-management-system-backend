package com.example.projectmanagementTool.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private Long id;
    private String content;
    private String authorEmail;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String authorEmail, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
