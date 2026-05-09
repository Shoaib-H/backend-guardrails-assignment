package com.example.guardrails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NotificationService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void handleNotification(Long userId, String message) {

		String cooldownKey = "notif:user:" + userId;

		Boolean exists = redisTemplate.hasKey(cooldownKey);

		if (Boolean.TRUE.equals(exists)) {

			redisTemplate.opsForList().rightPush("user:" + userId + ":pending_notifs", message);

			System.out.println("Notification Added To Queue");
		} else {

			System.out.println("Push Notification Sent To User");

			redisTemplate.opsForValue().set(cooldownKey, "active", Duration.ofMinutes(15));
		}
	}
}