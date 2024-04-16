package com.csye6220.finalProject.dao.impl;

import com.csye6220.finalProject.dao.CommentDAO;
import com.csye6220.finalProject.dao.DAO;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private final SessionFactory sessionFactory = DAO.getsessionFactory();
    @Override
    public Comment saveComment(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(comment);
        tx.commit();
        session.close();
        return comment;
    }

    @Override
    public List<Comment> findByPostId(long postId) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Comment c WHERE c.post.postId = :postId";
        List<Comment> comments = session.createQuery(hql).setParameter("postId", postId).list();
        tx.commit();
        session.close();
        return comments;
    }

    @Override
    public Comment findByCommentId(long commentId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Comment comment = (Comment) session.get(Comment.class,commentId);
        tx.commit();
        session.close();
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(comment);
        tx.commit();
        session.close();
        return comment;
    }

    @Override
    public void deleteById(long commentId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Comment comment = (Comment) session.get(Comment.class, commentId);
        session.delete(comment);
        tx.commit();
        session.close();
    }

    @Override
    public Comment findByUserId(long userId) {
        return null;
    }
}
