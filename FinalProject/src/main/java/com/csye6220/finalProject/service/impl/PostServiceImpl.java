package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.PostDAO;
import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.PostService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public PostServiceImpl(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }
    @Override
    public Post createPost(PostDto postDto, String username) {

        User user = userDAO.findByUserName(username);
        System.out.println("createPost : " + user.getUsername());

        Post post = new Post();
        post.setPostName(postDto.getPostName());
        post.setUrl(postDto.getUrl());
        post.setDescription(postDto.getDescription());
        post.setVoteCount(0);
        post.setUser(user);
        post.setCreatedDate(Instant.now());
        return postDAO.savePost(post);

    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postDAO.getAllPosts();
        return posts;
    }

    @Override
    public Post getPostById(long postId) {
        Post post = postDAO.getPostById(postId);
        return post;
    }

    @Override
    public Post updatePost(Long postId, PostDto postDto) {
        Post existingPost = postDAO.getPostById(postId);
        if(existingPost == null){
            throw new ResourceNotFoundException("Post Not found with id: " + postId);
        }
        existingPost.setDescription(postDto.getDescription());
        existingPost.setPostName(postDto.getPostName());
        existingPost.setUrl(postDto.getUrl());
        return postDAO.updatePost(existingPost);
    }

    public void deletePost(long postId) {
        Post post = postDAO.getPostById(postId);
        if(post == null){
            throw new ResourceNotFoundException("Post Not Found with id: " + postId);
        }else {
            postDAO.deletePost(postId);
        }
    }

    @Override
    public List<Post> getPostByUsername(String username) {
        List<Post> posts = postDAO.getPostByUsername(username);
        if(posts != null){
            return posts;
        }else {
            throw new ResourceNotFoundException("Post not found for user: "+ username);
        }
    }
}
