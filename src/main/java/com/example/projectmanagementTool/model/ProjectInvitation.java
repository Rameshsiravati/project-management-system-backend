package com.example.projectmanagementTool.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_invitations")
public class ProjectInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "invited_user_id", nullable = false)
    private User invitedUser;

    @ManyToOne
    @JoinColumn(name = "invited_by_id", nullable = false)
    private User invitedBy;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    public ProjectInvitation() {}

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public User getInvitedUser() {
        return invitedUser;
    }

    public User getInvitedBy() {
        return invitedBy;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public void setInvitedBy(User invitedBy) {
        this.invitedBy = invitedBy;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
