package com.bsl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsl.entity.Post;
import com.bsl.service.PostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/getAllPosts")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		Optional<Post> post = postService.getPostById(id);
		if (post.isPresent()) {
			return ResponseEntity.ok(post.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/createPost")
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable Long id) {
		postService.deletePost(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
		Post updatedPost = postService.updatePost(id, post);
		if (updatedPost != null) {
			return ResponseEntity.ok(updatedPost);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/getAllPostByUserId/{userId}")
	public List<Post> findAllPostByUserId(@PathVariable Long userId) {
		return postService.findAllPostByUserId(userId);
	}
}