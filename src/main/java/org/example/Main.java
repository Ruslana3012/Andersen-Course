package org.example;

import org.example.dao.TicketServiceDAO;
import org.example.dao.UserServiceDao;
import org.example.entity.TicketEntity;
import org.example.entity.TicketType;
import org.example.entity.UserEntity;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        TicketServiceDAO ticketDAO = new TicketServiceDAO();
        UserServiceDao userDAO = new UserServiceDao();

        TicketEntity ticket1 = new TicketEntity(1, TicketType.DAY, new Timestamp(15667), 1);
        TicketEntity ticket2 = new TicketEntity(2, TicketType.DAY, new Timestamp(15664), 1);
        UserEntity user1 = new UserEntity(1, "Ruslana", new Timestamp(12334));
        UserEntity user2 = new UserEntity(2, "Ruslana", new Timestamp(12335));

        userDAO.saveUser(user1);
        userDAO.saveUser(user2);
        ticketDAO.saveTicket(ticket1);
        ticketDAO.saveTicket(ticket2);

        System.out.println(ticketDAO.fetchTicketById(1));
        System.out.println(ticketDAO.fetchTicketByUserId(1));
        System.out.println(userDAO.fetchUserById(2));

        System.out.println(ticketDAO.updateTicketType(1, new TicketEntity(1, TicketType.WEEK, new Timestamp(15667), 1)));

        userDAO.deleteUserByIdANdAllTheirTickets(1);
        System.out.println(ticketDAO.fetchTicketByUserId(1));
    }
}