package com.example.projectmanagementTool.controller;

import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.User;
import com.example.projectmanagementTool.service.ProjectService;
import com.example.projectmanagementTool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    private  UserService userService;

    public ProjectController(ProjectService projectService,
                             UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping
    public Project createProject(@RequestBody Project project,
                                 Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return projectService.createProject(project, user);
    }

    @GetMapping
    public List<Project> getMyProjects(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return projectService.getProjectsByOwner(user);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id,
                                  Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return projectService.getProjectById(id, user);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id,
                                 @RequestBody Project project,
                                 Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return projectService.updateProject(id, project, user);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id,
                              Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        projectService.deleteProject(id, user);
    }
}
