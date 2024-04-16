package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.Comment;

import java.util.List;

public interface CommentDAO {

    Comment saveComment(Comment comment);
    List<Comment> findByPostId(long postId);
    Comment findByCommentId(long commentId);
    Comment updateComment(Comment comment);
    void deleteById(long commentId);

    Comment findByUserId(long userId);

}
