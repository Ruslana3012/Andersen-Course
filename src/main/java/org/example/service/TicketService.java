package org.example.service;

import org.example.annotation.AnnotationHandler;
import org.example.model.Ticket;
import org.example.model.user.Admin;
import org.example.model.user.Client;

import java.time.Instant;

public class TicketService {

    public static void main(String[] args) {
        Ticket limitedTicket = new Ticket("Hall 2", "324", Instant.now().getEpochSecond());

        AnnotationHandler.handleNullableWarnings(new Ticket());

        limitedTicket.share("+123456789");
        limitedTicket.share("+123456789", "email@gmail.com");

        Client client = new Client();
        Admin admin = new Admin();

        client.printRole();
        admin.printRole();


        System.out.println("\nThe default implementation: ");
        client.print();

        System.out.println("\nOverride implementation: ");
        admin.print();
    }
}
