package com.csye6220.finalProject.service;

import com.csye6220.finalProject.dto.VoteDto;
import org.apache.coyote.BadRequestException;

public interface VoteService {

    void upvotePost(long postId, String username) throws BadRequestException;
    void downvotePost(long postId, String username) throws BadRequestException;
}
