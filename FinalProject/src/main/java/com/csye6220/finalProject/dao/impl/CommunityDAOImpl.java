package com.csye6220.finalProject.dao.impl;

import com.csye6220.finalProject.dao.CommunityDAO;
import com.csye6220.finalProject.dao.DAO;
import com.csye6220.finalProject.model.Community;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityDAOImpl implements CommunityDAO {

    private final SessionFactory sessionFactory = DAO.getsessionFactory();

    @Override
    public Community create(Community community) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(community);
        tx.commit();
        session.close();
        return community;
    }

    @Override
    public List<Community> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Community> communityList = session.createQuery("FROM Community ").list();
        tx.commit();
        session.close();
        return communityList;
    }

    @Override
    public Community findByCommunityId(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Community community = (Community) session.get(Community.class, id);
        tx.commit();
        session.close();
        return community;
    }

    @Override
    public Community update(Community community) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(community);
        tx.commit();
        session.close();
        return community;
    }
}
