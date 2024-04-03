package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.User;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    public SessionFactory sessionFactory;

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void deletUser(String userId) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public User getUserByemailId(String emailId) {
        return null;
    }
}
