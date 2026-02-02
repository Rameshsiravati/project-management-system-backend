package com.example.projectmanagementTool.controller;

import com.example.projectmanagementTool.model.*;
import com.example.projectmanagementTool.service.IssueService;
import com.example.projectmanagementTool.service.ProjectService;
import com.example.projectmanagementTool.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;
    private final ProjectService projectService;
    private final UserService userService;

    public IssueController(IssueService issueService,
                           ProjectService projectService,
                           UserService userService) {
        this.issueService = issueService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping
    public Issue createIssue(@RequestParam Long projectId,
                             @RequestBody Issue issue,
                             Authentication authentication) {

        User user = userService.getUserByEmail(authentication.getName());
        Project project = projectService.getProjectById(projectId, user);

        return issueService.createIssue(issue, project, user);
    }

    @GetMapping("/project/{projectId}")
    public List<Issue> getIssuesByProject(@PathVariable Long projectId,
                                          Authentication authentication) {

        User user = userService.getUserByEmail(authentication.getName());
        Project project = projectService.getProjectById(projectId, user);

        return issueService.getIssuesByProject(project);
    }
}
