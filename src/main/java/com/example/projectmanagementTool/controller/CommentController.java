package com.example.projectmanagementTool.controller;

import com.example.projectmanagementTool.model.*;
import com.example.projectmanagementTool.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final IssueService issueService;
    private final ProjectService projectService;
    private final UserService userService;

    public CommentController(CommentService commentService,
                             IssueService issueService,
                             ProjectService projectService,
                             UserService userService) {
        this.commentService = commentService;
        this.issueService = issueService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping
    public Comment addComment(@RequestParam Long issueId,
                              @RequestBody String content,
                              Authentication authentication) {

        User user = userService.getUserByEmail(authentication.getName());

        Issue issue = issueService.getIssueById(issueId); // add method
        Project project = issue.getProject();

        // Authorization: user must have access to project
        projectService.getProjectById(project.getId(), user);

        return commentService.addComment(content, issue, user);
    }

    @GetMapping("/issue/{issueId}")
    public List<Comment> getComments(@PathVariable Long issueId,
                                     Authentication authentication) {

        User user = userService.getUserByEmail(authentication.getName());
        Issue issue = issueService.getIssueById(issueId);

        projectService.getProjectById(issue.getProject().getId(), user);

        return commentService.getCommentsByIssue(issue);
    }
}
