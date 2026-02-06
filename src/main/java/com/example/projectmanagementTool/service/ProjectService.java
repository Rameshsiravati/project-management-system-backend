package com.example.projectmanagementTool.service;

import com.example.projectmanagementTool.dto.ProjectResponse;
import com.example.projectmanagementTool.dto.UserResponse;
import com.example.projectmanagementTool.exception.AccessDeniedException;
import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.User;
import com.example.projectmanagementTool.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private  ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project, User owner) {
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    public List<ProjectResponse> getProjectsByOwner(User owner) {
        return projectRepository.findByOwner(owner)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Project getProjectById(Long projectId, User owner) {
        return projectRepository.findByIdAndOwner(projectId, owner)
                .orElseThrow(() -> new AccessDeniedException("Access denied or project not found"));
    }

    public Project updateProject(Long projectId, Project updatedProject, User owner) {
        Project existingProject = getProjectById(projectId, owner);
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long projectId, User owner) {
        Project project = getProjectById(projectId, owner);
        projectRepository.delete(project);
    }
    private ProjectResponse mapToDto(Project project) {
        User owner = project.getOwner();
        UserResponse ownerDto =
                new UserResponse(owner.getId(), owner.getName(), owner.getEmail());

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                ownerDto
        );
    }
}
