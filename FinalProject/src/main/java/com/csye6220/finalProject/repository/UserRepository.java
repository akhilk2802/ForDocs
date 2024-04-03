package com.csye6220.finalProject.repository;

import com.csye6220.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
