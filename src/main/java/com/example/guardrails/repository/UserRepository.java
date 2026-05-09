package com.example.guardrails.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guardrails.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}