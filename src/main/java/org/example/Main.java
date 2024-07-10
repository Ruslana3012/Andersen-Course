package org.example;

import org.example.entity.Ticket;
import org.example.entity.TicketType;
import org.example.entity.User;
import org.example.service.impl.TicketServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.util.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.sql.Timestamp;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        Resource resource = context.getResource("classpath:tickets.json");
        List<Ticket> tickets = FileService.loadTicketsFromResources(resource);
        System.out.println("Get all tickets from file: ");
        tickets.forEach(System.out::println);

        TicketServiceImpl ticketService = context.getBean(TicketServiceImpl.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        User user = new User("Ivanna", new Timestamp(12334));
        userService.saveUser(user);

        Ticket ticket = new Ticket(TicketType.DAY, new Timestamp(15667), userService.fetchUserById(2L));
        ticketService.saveTicket(ticket);

        userService.updateUserAndCreateTicket(2L, "Ruslana", ticket);
        System.out.println("user.status=ON");
//        System.out.println("user.status=OFF");
        System.out.println("Checking that user was created: " + userService.fetchUserById(2L));
        System.out.println("Checking that ticket was added by user_id: " + ticketService.fetchTicketsByUserId(2L));
    }
}