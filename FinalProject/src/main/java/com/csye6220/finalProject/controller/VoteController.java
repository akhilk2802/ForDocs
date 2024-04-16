package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.service.VoteService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes/")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/upvote/{postId}")
    public ResponseEntity<?> upVotePost(@PathVariable long postId, @AuthenticationPrincipal UserDetails userDetails) throws BadRequestException {
        String username = userDetails.getUsername();
        System.out.println("Username from upvote: " + username);
        voteService.upvotePost(postId, username);

        return ResponseEntity.ok("Post upvoted successfully");
    }

    @PostMapping("/downvote/{postId}")
    public ResponseEntity<?> downVotePost(@PathVariable long postId, @AuthenticationPrincipal UserDetails userDetails) throws BadRequestException {
        String username = userDetails.getUsername();
        System.out.println("username from controller: " + username);
        voteService.downvotePost(postId, username);

        return ResponseEntity.ok("Post downvoted successfully");
    }

}
