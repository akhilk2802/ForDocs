package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.PostDAO;
import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.dao.VoteDAO;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.model.Vote;
import com.csye6220.finalProject.model.VoteType;
import com.csye6220.finalProject.service.VoteService;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteDAO voteDAO;
    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public VoteServiceImpl(VoteDAO voteDAO, PostDAO postDAO, UserDAO userDAO) {
        this.voteDAO = voteDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void upvotePost(long postId, String username) throws BadRequestException {
        Post post = postDAO.getPostById(postId);
        User user = userDAO.findByUserName(username);
        System.out.println("From voteService, post: " + post  + " user: " + user);
        if(post == null){
            throw new ResourceNotFoundException("post not found with post id : " + postId);
        }
        if (user == null){
            throw new ResourceNotFoundException("user not found with username : " + username);
        }

        Vote existingVote = voteDAO.findByPostAndUser(post, user);


        if(existingVote != null){
            System.out.println("I am here hkfhslkjhflsabhg");
            if(existingVote.getVoteType() == VoteType.UPVOTE){
                System.out.println(existingVote.getVoteType());
                throw new IllegalStateException("You have already upvoted this post.");
            }else{
                existingVote.setVoteType(VoteType.UPVOTE);
                post.setVoteCount(post.getVoteCount() + 1);
                postDAO.updatePost(post);

                voteDAO.updateVote(existingVote);
            }
        }else{
            Vote vote = new Vote();
            vote.setPost(post);
            vote.setUser(user);
            vote.setVoteType(VoteType.UPVOTE);

            voteDAO.saveVote(vote);
            post.setVoteCount(post.getVoteCount() + 1);
            postDAO.updatePost(post);

        }
    }

    @Override
    public void downvotePost(long postId, String username) throws BadRequestException {

        Post post = postDAO.getPostById(postId);
        User user = userDAO.findByUserName(username);
        System.out.println("From voteService, post: " + post  + " user: " + user);
        if(post == null){
            throw new ResourceNotFoundException("post not found with post id : " + postId);
        }
        if (user == null){
            throw new ResourceNotFoundException("user not found with username : " + username);
        }

        Vote existingVote = voteDAO.findByPostAndUser(post, user);

        if(existingVote != null){
            System.out.println("I am here in downvote service");
            if(existingVote.getVoteType() == VoteType.DOWNVOTE){
                System.out.println(existingVote.getVoteType());
                throw new IllegalStateException("You have already downvoted this post.");
            }else{
                existingVote.setVoteType(VoteType.DOWNVOTE);
                post.setVoteCount(post.getVoteCount() - 1);
                postDAO.updatePost(post);

                voteDAO.updateVote(existingVote);
            }
        }else{
            Vote vote = new Vote();
            vote.setPost(post);
            vote.setUser(user);
            vote.setVoteType(VoteType.DOWNVOTE);

            voteDAO.saveVote(vote);
            post.setVoteCount(post.getVoteCount() - 1);
            postDAO.updatePost(post);

        }
    }
}
