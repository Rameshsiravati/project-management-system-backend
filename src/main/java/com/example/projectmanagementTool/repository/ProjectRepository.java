package com.example.projectmanagementTool.repository;

import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
    Optional<Project> findByIdAndOwner(Long id, User owner);
}

