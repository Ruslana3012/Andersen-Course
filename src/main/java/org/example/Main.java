package org.example;

import org.example.config.FlywayConfiguration;
import org.example.dao.TicketServiceDAO;
import org.example.dao.UserServiceDAO;
import org.example.entity.TicketEntity;
import org.example.entity.TicketType;
import org.example.entity.UserEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws IOException {
        FlywayConfiguration.applyDbMigrations();
        TicketServiceDAO ticketDAO = new TicketServiceDAO();
        UserServiceDAO userDAO = new UserServiceDAO();

        UserEntity user = new UserEntity("Ivanna", new Timestamp(12334));
        userDAO.saveUser(user);

        TicketEntity ticket1 = new TicketEntity(TicketType.DAY, new Timestamp(15667), 3);

        ticketDAO.saveTicket(ticket1);

        System.out.println("Get ticket by id [1]: " + ticketDAO.fetchTicketById(1));
        System.out.println("Get tickets by user id [1]: " + ticketDAO.fetchTicketsByUserId(1));
        System.out.println("Get user by id [2]: " + userDAO.fetchUserById(2));

        System.out.println("Update ticket type from [DAY] to [WEEK]: " + ticketDAO.updateTicketType(1, new TicketEntity(TicketType.WEEK, new Timestamp(15667), 1)));

        System.out.println("Get all tickets for user [Ivanna]: " + ticketDAO.fetchTicketsByUserId(userDAO.getUserIdByName("Ivanna")));
        userDAO.deleteUserByIdANdAllTheirTickets(userDAO.getUserIdByName("Ivanna"));
        System.out.println("Check that user [Ivanna] was deleted: " + userDAO.getAllUsers());
        System.out.println("Check that tickets was deleted after user deletion [Ivanna]: " + ticketDAO.fetchTicketsByUserId(3));
    }
}