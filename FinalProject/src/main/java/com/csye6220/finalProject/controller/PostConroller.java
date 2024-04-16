package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.PostService;
import com.csye6220.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostConroller {


    private final PostService postService;


    public PostConroller(PostService postService, UserService userService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto, @RequestHeader("Authorization") String token){
        String username = JwtProvider.getUserNameFromJWT(token);
        System.out.println("username: " + username);
        Post post = postService.createPost(postDto, username);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost(){
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable long postId){
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        Post updatedPost = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deleteUser(@PathVariable long postId){
        postService.deletePost(postId);
        return new ResponseEntity<String>("post deleted successfully", HttpStatus.OK);
    }

}
