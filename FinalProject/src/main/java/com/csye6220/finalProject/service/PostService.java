package com.csye6220.finalProject.service;

import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto, String username);
    List<Post> getAllPosts();

    Post getPostById(long postId);
    Post updatePost(Long postId, PostDto postDto);
    void deletePost(long postId);

    List<Post> getPostByUsername(String username);
}
