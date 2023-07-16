package com.bsl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.entity.Post;
import com.bsl.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	public Optional<Post> getPostById(Long id) {
		return postRepository.findById(id);
	}

	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	public Post updatePost(Long id, Post post) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if (optionalPost.isPresent()) {
			Post existingPost = optionalPost.get();
			existingPost.setPostName(post.getPostName());
			existingPost.setPostDescription(post.getPostDescription());
			existingPost.setPostImage(post.getPostImage());
			existingPost.setPostHashtag(post.getPostHashtag());
			existingPost.setApprovalStatus(post.isApprovalStatus());
			existingPost.setUserId(post.getUserId());
			return postRepository.save(existingPost);
		}
		return null;
	}

	public List<Post> findAllPostByUserId(Long userId) {

		return postRepository.getAllPostByUserId(userId);
	}

}