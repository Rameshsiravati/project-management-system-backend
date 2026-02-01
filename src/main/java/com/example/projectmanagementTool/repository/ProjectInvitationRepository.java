package com.example.projectmanagementTool.repository;

import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.ProjectInvitation;
import com.example.projectmanagementTool.model.User;
import com.example.projectmanagementTool.model.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectInvitationRepository extends JpaRepository<ProjectInvitation, Long> {

    List<ProjectInvitation> findByInvitedUserAndStatus(User user, InvitationStatus status);

    Optional<ProjectInvitation> findByProjectAndInvitedUser(Project project, User user);
}
