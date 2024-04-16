package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.CommentDto;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable long postId, @RequestBody String commentText, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        System.out.println("username from create comment controller: " + username);

        Comment comment = commentService.createComment(postId, commentText, username);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/bypost/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable long postId){

        List<Comment> comments =  commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable long commentId, @RequestBody String commentText, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Comment comment = commentService.updateComment(commentId, commentText, username);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        commentService.deleteComment(commentId, username);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
