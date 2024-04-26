package com.csye6220.finalProject.service;

import com.csye6220.finalProject.model.Community;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;

import java.util.List;

public interface CommunityService {

    Community create(String name, String desc, String username);

    List<Community> getAll();
    List<Post> getPostsByCommunityId(long id);

    void joinCommunity(long communityId, String username);

    List<User> getMembers(long communityId);

    Community findById(long id);

}
