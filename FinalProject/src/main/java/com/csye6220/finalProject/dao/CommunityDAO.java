package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.Community;

import java.util.List;

public interface CommunityDAO {

    Community create(Community community);
    List<Community> getAll();

    Community findByCommunityId(long id);

    Community update(Community community);
}
