package com.csye6220.finalProject.dto;

public class CommentDto {
    private String commentText;

    public CommentDto(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
