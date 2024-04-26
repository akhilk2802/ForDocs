package com.csye6220.finalProject.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long communityId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
    @Column(name = "created_date")
    private Instant createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdBy;
    @ManyToMany(mappedBy = "communities",fetch = FetchType.EAGER)
    private List<User> members = new ArrayList<>();


    public Community(Long communityId, String name, String description, List<Post> posts, Instant createdDate, User createdBy) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }


    public Community(Long communityId, String name, String description, List<Post> posts, Instant createdDate, User createdBy, List<User> members) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.members = members;
    }

    public Community() {

    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
