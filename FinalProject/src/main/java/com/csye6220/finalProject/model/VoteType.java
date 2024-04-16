package com.csye6220.finalProject.model;

import com.csye6220.finalProject.exception.ResourceNotFoundException;
import jakarta.persistence.Entity;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum VoteType {
    UPVOTE, DOWNVOTE;
    VoteType() {
    }
}
