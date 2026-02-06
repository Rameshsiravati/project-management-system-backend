package com.example.projectmanagementTool.service;

import com.example.projectmanagementTool.model.*;
import com.example.projectmanagementTool.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(String content, Issue issue, User user) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIssue(issue);
        comment.setCreatedBy(user);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByIssue(Issue issue) {
        return commentRepository.findByIssue(issue);
    }
}
