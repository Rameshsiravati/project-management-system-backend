package com.example.projectmanagementTool.dto;

import com.example.projectmanagementTool.model.IssueStatus;

public class IssueResponse {

    private Long id;
    private String title;
    private String description;
    private IssueStatus status;

    public IssueResponse(Long id, String title, String description, IssueStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus getStatus() {
        return status;
    }
}
