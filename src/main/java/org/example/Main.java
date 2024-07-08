package org.example;

import org.example.entity.Ticket;
import org.example.entity.TicketType;
import org.example.entity.User;
import org.example.service.impl.TicketServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        TicketServiceImpl ticketService = context.getBean(TicketServiceImpl.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        User user = new User("Ivanna", new Timestamp(12334));
        userService.saveUser(user);

        Ticket ticket1 = new Ticket(TicketType.DAY, new Timestamp(15667), user);
        ticketService.saveTicket(ticket1);

        System.out.println("Get ticket by id [1]: " + ticketService.fetchTicketById(1L));
        System.out.println("Get tickets by user id [1]: " + ticketService.fetchTicketsByUserId(1L));
        System.out.println("Get user by id [2]: " + userService.fetchUserById(2L));

        System.out.println("Update ticket type from [WEEK] to [YEAR]: " + ticketService.updateTicketType(1L, new Ticket(TicketType.YEAR, new Timestamp(15667), user)));

        System.out.println("Get all tickets for user [Ruslana]: " + ticketService.fetchTicketsByUserId(1L));
        userService.deleteUserByIdANdAllTheirTickets(1L);
        System.out.println("Check that user [Ruslana] was deleted: " + userService.getAllUsers());
        System.out.println("Check that tickets was deleted after user deletion [Ruslana]: " + ticketService.fetchTicketsByUserId(1L));
    }
}