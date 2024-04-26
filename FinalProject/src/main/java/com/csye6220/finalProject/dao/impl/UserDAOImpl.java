package com.csye6220.finalProject.dao.impl;

import com.csye6220.finalProject.dao.DAO;
import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
//    @Autowired
    private final SessionFactory sessionFactory = DAO.getsessionFactory();
    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        tx.commit();
        session.close();
        return users;
    }
    @Override
    public void deleteUser(long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, userId);
        session.delete(user);
        tx.commit();
        session.close();
    }
    @Override
    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        return user;
    }
    @Override
    public User getUserById(long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, userId);
        tx.commit();
        session.close();
        return user;
    }
    public User findByUserName(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        User user = query.uniqueResult();
        tx.commit();
        session.close();
        return user;
    }
    @Override
    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
        return user;
    }

    @Override
    public void leaveCommunity(long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            User user = new User();
            user.setUserId(userId);
            String hql = "DELETE FROM User_Community uc WHERE uc.user.id = :userid";
            Query q = session.createQuery(hql);
            q.setParameter("userid", userId);
        }catch (Exception e){
            e.getMessage();
        }

        tx.commit();
        session.close();
    }
}