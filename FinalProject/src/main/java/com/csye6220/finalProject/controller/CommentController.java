package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.CommentDto;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{postId}")
    public String createComment(@PathVariable long postId, @RequestParam("commentText") String commentText, HttpSession session){

        String token = (String) session.getAttribute("token");
        if(token == null){
            session.setAttribute("notAuth", true);
            return "redirect:/api/auth/showLogin";
        }
        String username = JwtProvider.getUserNameFromJWT(token);
        System.out.println("username from create comment controller: " + username);

        Comment comment = commentService.createComment(postId, commentText, username);
        return "redirect:/api/post/all";
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

    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable long commentId, HttpSession session){
        String token = (String) session.getAttribute("token");
        String username = JwtProvider.getUserNameFromJWT(token);
//        String username = userDetails.getUsername();
        commentService.deleteComment(commentId, username);
        return "redirect:/api/myprofile";
    }
}
