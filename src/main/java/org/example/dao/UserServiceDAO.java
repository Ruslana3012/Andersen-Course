package org.example.dao;

import org.example.entity.TicketEntity;
import org.example.entity.UserEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserServiceDAO {
    public void saveUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error saving user.", e);
        }
    }

    public List<UserEntity> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM UserEntity", UserEntity.class).list();
        }
    }

    public UserEntity fetchUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UserEntity.class, userId);
        }
    }

    public int getUserIdByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            org.hibernate.query.Query query = session.createQuery("SELECT id FROM UserEntity WHERE name = :name");
            query.setParameter("name", name);
            return (int) query.uniqueResult();
        }
    }

    public void deleteUserByIdANdAllTheirTickets(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String deleteTicketsHQL = "DELETE FROM TicketEntity WHERE userId = :userId";
            Query deleteTicketsQuery = session.createQuery(deleteTicketsHQL);
            deleteTicketsQuery.setParameter("userId", userId);
            deleteTicketsQuery.executeUpdate();

            String deleteUserHQL = "DELETE FROM UserEntity WHERE id = :userId";
            Query deleteUserQuery = session.createQuery(deleteUserHQL);
            deleteUserQuery.setParameter("userId", userId);
            deleteUserQuery.executeUpdate();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateUserAndTickets(UserEntity user, List<TicketEntity> tickets) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            for (TicketEntity ticket : tickets) {
                session.update(ticket);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
