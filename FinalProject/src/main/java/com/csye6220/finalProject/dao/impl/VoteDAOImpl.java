package com.csye6220.finalProject.dao.impl;

import com.csye6220.finalProject.dao.DAO;
import com.csye6220.finalProject.dao.VoteDAO;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.model.Vote;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDAOImpl implements VoteDAO {

    private final SessionFactory sessionFactory = DAO.getsessionFactory();
    @Override
    public boolean existByPostAndUser(Post post, User user) {
        try{
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

            String jpql = "SELECT COUNT(v) FROM Vote v WHERE v.post = :post AND v.user = :user";
            Long count = (Long) session.createQuery(jpql).setParameter("post", post).setParameter("user", user).getSingleResult();
            tx.commit();
            session.close();
            return count>0;
        }catch (Exception e){
            System.out.println("exist function : " + e.getMessage());
        }return false;
    }

    @Override
    public Vote saveVote(Vote vote) {
        try{Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(vote);
        tx.commit();
        session.close();
        return vote;}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Vote findByPostAndUser(Post post, User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();


        String jpql = "SELECT v FROM Vote v WHERE v.post = :post AND v.user = :user";
        Vote vote = session.createQuery(jpql, Vote.class).setParameter("user", user).setParameter("post", post).uniqueResult();
        System.out.println("vote kjfhskhgiukshaiuh : " + vote);
        tx.commit();
        session.close();
        return vote;
    }

    @Override
    public Vote updateVote(Vote vote) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(vote);
        tx.commit();
        session.close();
        return vote;
    }
}
