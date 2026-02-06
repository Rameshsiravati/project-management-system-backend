package com.example.projectmanagementTool.dto;

public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private UserResponse owner;

    public ProjectResponse(Long id, String title, String description, UserResponse owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
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

    public UserResponse getOwner() {
        return owner;
    }
}
