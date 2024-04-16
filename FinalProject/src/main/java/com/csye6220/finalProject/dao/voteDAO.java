package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.model.Vote;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDAO {

    boolean existByPostAndUser(Post post, User user);

    Vote saveVote(Vote vote);

    Vote findByPostAndUser(Post post, User user);

    Vote updateVote(Vote vote);
//    Vote updateVote(Vote vote);
}
