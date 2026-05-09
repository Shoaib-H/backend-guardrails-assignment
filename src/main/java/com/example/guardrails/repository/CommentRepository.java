package com.example.guardrails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guardrails.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}