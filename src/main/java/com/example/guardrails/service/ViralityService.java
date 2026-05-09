package com.example.guardrails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViralityService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void increaseScore(Long postId, int points){

        String key = "post:" + postId + ":virality";

        redisTemplate.opsForValue().increment(key, points);
    }

    public String getScore(Long postId){

        String key = "post:" + postId + ":virality";

        return redisTemplate.opsForValue().get(key);
    }
}