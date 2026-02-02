package com.example.projectmanagementTool.repository;

import com.example.projectmanagementTool.model.Issue;
import com.example.projectmanagementTool.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByProject(Project project);
}
