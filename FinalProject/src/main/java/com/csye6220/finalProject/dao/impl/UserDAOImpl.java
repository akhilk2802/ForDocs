package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

//    @Autowired
    private final SessionFactory sessionFactory = DAO.getsessionFactory();
    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        System.out.println(session);
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        tx.commit();
        session.close();
        System.out.println(users);
        return users;
    }
    @Override
    public void deleteUser(long userId) {
        Session session = sessionFactory.openSession();
        System.out.println(userId);
        Transaction tx = session.beginTransaction();
        User user = (User) session.get(User.class, userId);
        System.out.println(user);
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

    @Override
    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
        return user;
    }
}
