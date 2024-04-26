package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.CommunityDAO;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.Community;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.CommunityService;
import com.csye6220.finalProject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommunityServiceImpl implements CommunityService {

    private final UserService userService;
    private final CommunityDAO communityDAO;

    public CommunityServiceImpl(UserService userService, CommunityDAO communityDAO) {
        this.userService = userService;
        this.communityDAO = communityDAO;
    }

    @Override
    public Community create(String name, String desc, String username) {

        User user = userService.getUserByUsername(username);
        Community community = new Community();
        community.setDescription(desc);
        community.setName(name);
        community.setCreatedBy(user);

        return communityDAO.create(community);
    }

    @Override
    public List<Community> getAll() {
        List<Community> communityList = communityDAO.getAll();
        return communityList;
    }

    @Override
    public List<Post> getPostsByCommunityId(long id) {
        Community community = communityDAO.findByCommunityId(id);
        if(community != null){
            return community.getPosts();
        }else {
            throw  new ResourceNotFoundException("Community not found with id : " + id);
        }
    }

    @Override
    public void joinCommunity(long communityId, String username) {
        try{
            User user = userService.getUserByUsername(username);
            Community existingCommunity = communityDAO.findByCommunityId(communityId);
            List<User> members = existingCommunity.getMembers();
            members.add(user);
            communityDAO.update(existingCommunity);
            user.getCommunities().add(existingCommunity);
            userService.saveUser(user);
            Community uc = communityDAO.findByCommunityId(communityId);
            List<User> m = uc.getMembers();
            for(User u : m){
                System.out.println(u.getUsername());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<User> getMembers(long communityId) {
        Community community = communityDAO.findByCommunityId(communityId);
        List<User> members = community.getMembers();
        return members;
    }

    @Override
    public Community findById(long id) {
        Community community = communityDAO.findByCommunityId(id);
        return community;
    }

}
