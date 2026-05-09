package com.example.guardrails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guardrails.entity.Bot;

public interface BotRepository extends JpaRepository<Bot, Long> {
}
