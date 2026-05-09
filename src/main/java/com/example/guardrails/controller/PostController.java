package com.example.guardrails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.guardrails.entity.Comment;
import com.example.guardrails.entity.Post;
import com.example.guardrails.repository.CommentRepository;
import com.example.guardrails.repository.PostRepository;
import com.example.guardrails.service.GuardrailService;
import com.example.guardrails.service.NotificationService;
import com.example.guardrails.service.ViralityService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ViralityService viralityService;

	@Autowired
	private GuardrailService guardrailService;

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public Post createPost(@RequestBody Post post) {

		post.setCreatedAt(LocalDateTime.now());

		return postRepository.save(post);
	}

	@PostMapping("/{postId}/comments")
	public Object addComment(@PathVariable Long postId, @RequestBody Comment comment) {

		if (comment.getDepthLevel() > 20) {

			return "Depth level exceeded";
		}

		if (comment.getAuthorType().equals("BOT")) {

			boolean allowed = guardrailService.allowBotReply(postId);

			if (!allowed) {

				return "429 Too Many Requests";
			}

			viralityService.increaseScore(postId, 1);
			notificationService.handleNotification(1L, "Bot replied to your post");
		} else {

			viralityService.increaseScore(postId, 50);
		}

		comment.setPostId(postId);

		comment.setCreatedAt(LocalDateTime.now());

		return commentRepository.save(comment);
	}

	@PostMapping("/{postId}/like")
	public String likePost(@PathVariable Long postId) {

		viralityService.increaseScore(postId, 20);

		return "Post liked";
	}

	@GetMapping("/{postId}/virality")
	public String getVirality(@PathVariable Long postId) {

		return viralityService.getScore(postId);
	}
}