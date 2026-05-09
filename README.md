# backend-guardrails-assignment

# Backend Guardrails Assignment

## Tech Stack
- Java 17
- Spring Boot 3
- MySQL
- Redis
- Docker

---

## Features

### APIs
- Create Post
- Add Comment
- Like Post
- Get Virality Score

### Redis Guardrails
- Horizontal Bot Limit (100 max)
- Vertical Depth Limit (20 max)
- Cooldown Protection

### Notification Engine
- Notification Queue
- Smart Notification Batching
- Scheduler Every 5 Minutes

---

## Run Project

### Start Docker Containers

```bash
docker compose up -d
```

### Run Spring Boot App

```bash
mvn spring-boot:run
```

---

## API Endpoints

### Create Post
POST /api/posts

### Add Comment
POST /api/posts/{postId}/comments

### Like Post
POST /api/posts/{postId}/like

### Get Virality
GET /api/posts/{postId}/virality

---

## Thread Safety

Redis atomic INCR operation is used to ensure
thread-safe concurrent bot counting.

Even if multiple requests hit simultaneously,
the bot count never exceeds the allowed limit.