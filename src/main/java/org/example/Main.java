package org.example;

import org.example.config.FlywayConfiguration;
import org.example.dao.TicketServiceDAO;
import org.example.dao.UserServiceDAO;
import org.example.entity.TicketEntity;
import org.example.entity.TicketType;
import org.example.entity.UserEntity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Properties properties = new Properties();
        InputStream input = Main.class.getClassLoader().getResourceAsStream("database.properties");
        properties.load(input);
        FlywayConfiguration.applyDbMigrations();
        Connection connection = DriverManager.getConnection(
                properties.getProperty("database.url"),
                properties.getProperty("database.user"),
                properties.getProperty("database.password"));
        TicketServiceDAO ticketDAO = new TicketServiceDAO(connection);
        UserServiceDAO userDAO = new UserServiceDAO(connection);

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