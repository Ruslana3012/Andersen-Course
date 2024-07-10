package org.example.dao;

import org.example.entity.TicketEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketServiceDAO {
    public void saveTicket(TicketEntity ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    public List<TicketEntity> getAllTickets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM TicketEntity", TicketEntity.class).list();
        }
    }

    public TicketEntity fetchTicketById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TicketEntity.class, id);
        }
    }

    public List<TicketEntity> fetchTicketsByUserId(int userId) {
        List<TicketEntity> tickets = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<TicketEntity> query = session.createQuery("FROM TicketEntity WHERE userId = :userId", TicketEntity.class);
            query.setParameter("userId", userId);
            tickets = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public TicketEntity updateTicketType(int ticketId, TicketEntity newTicket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TicketEntity currentTicket = session.get(TicketEntity.class, ticketId);
            if (currentTicket != null) {
                transaction = session.beginTransaction();
                currentTicket.setTicketType(newTicket.getTicketType());
                session.update(currentTicket);
                transaction.commit();
                return newTicket;
            }
            return null;
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

    public void deleteTicketByUserId(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TicketEntity ticket = session.get(TicketEntity.class, userId);
            if (ticket != null) {
                transaction = session.beginTransaction();
                session.delete(ticket);
                transaction.commit();
            }
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
}
