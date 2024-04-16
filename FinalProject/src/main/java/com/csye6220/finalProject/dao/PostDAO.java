package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO {

    Post savePost(Post post);
    List<Post> getAllPosts();

    Post updatePost(Post post);

    Post getPostById(Long postid);
    void deletePost(long postId);
}
