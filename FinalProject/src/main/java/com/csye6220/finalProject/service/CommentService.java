package com.csye6220.finalProject.service;


import com.csye6220.finalProject.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(long postId, String commentText, String username);
    List<Comment> getCommentsByPost(long postId);
    Comment updateComment(long commentId, String updatedText, String username);
    void deleteComment(long commentId, String username);
    Comment getCommentById(long commentId);
}
