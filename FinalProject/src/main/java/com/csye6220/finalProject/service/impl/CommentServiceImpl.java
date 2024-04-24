package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.CommentDAO;
import com.csye6220.finalProject.dao.PostDAO;
import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.exception.ValidationException;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;

    private final CommentDAO commentDAO;

    public CommentServiceImpl(PostDAO postDAO, UserDAO userDAO, CommentDAO commentDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public Comment createComment(long postId, String commentText, String username) {

        try{
            Post post = postDAO.getPostById(postId);
            User user = userDAO.findByUserName(username);
            Comment comment = new Comment();
            comment.setText(commentText);
            comment.setPost(post);
            comment.setUser(user);

            return commentDAO.saveComment(comment);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Comment> getCommentsByPost(long postId) {
        System.out.println("inside commentService : " + postId);
        return commentDAO.findByPostId(postId);
    }

    @Override
    public Comment updateComment(long commentId, String updatedText, String username) {
        Comment existingComment = commentDAO.findByCommentId(commentId);
        if(!existingComment.getUser().getUsername().equals(username) && !existingComment.getPost().getUser().getUsername().equals(username)){
            throw new ValidationException("User not authorized to perform this action");
        }
        existingComment.setText(updatedText);
        return commentDAO.updateComment(existingComment);
    }

    @Override
    public void deleteComment(long commentId, String username) {
        Comment existingComment = commentDAO.findByCommentId(commentId);
        if(!existingComment.getUser().getUsername().equals(username) && !existingComment.getPost().getUser().getUsername().equals(username)){
            throw new ValidationException("User not authorized to perform this action");
        }
        commentDAO.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(long commentId) {
        return null;
    }
}
