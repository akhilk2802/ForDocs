package com.csye6220.finalProject.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "comment_text")
    private String text;
    @Column(name = "created_date")
    private Instant createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;

    public Comment(Long commentId, String text, Post post, Instant createdDate, User user) {
        this.commentId = commentId;
        this.text = text;
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Comment(Long commentId, String text, Post post, Instant createdDate, User user, Post post1) {
        this.commentId = commentId;
        this.text = text;
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post1;
    }

    public Comment(){}

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
