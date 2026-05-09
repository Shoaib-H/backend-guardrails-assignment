package com.example.guardrails.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NotificationScheduler {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Scheduled(fixedRate = 300000)
	public void sweepNotifications() {

		Set<String> keys = redisTemplate.keys("user:*:pending_notifs");

		if (keys == null) {
			return;
		}

		for (String key : keys) {

			Long count = redisTemplate.opsForList().size(key);

			if (count != null && count > 0) {

				System.out.println("Summarized Push Notification : " + count + " new interactions");

				redisTemplate.delete(key);
			}
		}
	}
}
