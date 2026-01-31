package com.example.projectmanagementTool.repository;

import com.example.projectmanagementTool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);  //Optional helps avoid NullPointerExceptions by forcing explicit handling of missing values
}
