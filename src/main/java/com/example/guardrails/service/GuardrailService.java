package com.example.guardrails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GuardrailService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public boolean allowBotReply(Long postId) {

		String key = "post:" + postId + ":bot_count";

		Long count = redisTemplate.opsForValue().increment(key);

		if (count > 100) {

			redisTemplate.opsForValue().decrement(key);

			return false;
		}

		return true;
	}

	public boolean checkCooldown(Long botId, Long humanId) {

		String key = "cooldown:bot_" + botId + ":human_" + humanId;

		Boolean exists = redisTemplate.hasKey(key);

		if (Boolean.TRUE.equals(exists)) {

			return false;
		}

		redisTemplate.opsForValue().set(key, "active", Duration.ofMinutes(10));

		return true;
	}
}
