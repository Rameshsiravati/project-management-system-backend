package com.example.projectmanagementTool.service;

import com.example.projectmanagementTool.model.*;
import com.example.projectmanagementTool.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(Issue issue, Project project, User user) {
        issue.setProject(project);
        issue.setCreatedBy(user);
        issue.setStatus(IssueStatus.OPEN);
        return issueRepository.save(issue);
    }

    public List<Issue> getIssuesByProject(Project project) {
        return issueRepository.findByProject(project);
    }
}
