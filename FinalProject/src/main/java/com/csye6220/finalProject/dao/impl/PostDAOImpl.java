package com.csye6220.finalProject.dao.impl;

import com.csye6220.finalProject.dao.DAO;
import com.csye6220.finalProject.dao.PostDAO;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO {

    private final SessionFactory sessionFactory = DAO.getsessionFactory();
    @Override
    public Post savePost(Post post) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
        session.close();
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
        List<Post> posts = session.createQuery("FROM Post").list();
//        tx.commit();
//        session.close();
        return posts;
    }

    @Override
    public Post updatePost(Post post) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(post);
        tx.commit();
        session.close();
        return post;
    }

    @Override
    public Post getPostById(Long postid) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Post post = (Post) session.get(Post.class, postid);
        tx.commit();
        session.close();
        return post;
    }

    @Override
    public void deletePost(long postId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
//        Post post = (Post) session.get(Post.class, postId);
//        session.delete(post);
        String hql = "DELETE FROM Post p WHERE p.id = :postId";
        Query q = session.createQuery(hql);
        q.setParameter("postId", postId);
        q.executeUpdate();
        tx.commit();
        session.close();

    }

    @Override
    public List<Post> getPostByUsername(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<Post> query = session.createQuery(
                "SELECT p FROM Post p JOIN p.user u WHERE u.username = :username");
        query.setParameter("username", username);
        List<Post> posts = query.list();
        tx.commit();
        session.close();
        return posts;
    }
}
