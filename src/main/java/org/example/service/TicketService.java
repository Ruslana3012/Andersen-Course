package org.example.service;

import org.example.model.Ticket;

import java.math.BigDecimal;
import java.time.Instant;

public class TicketService {

    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        System.out.println(emptyTicket);

        Ticket fullTicket = new Ticket("1234", "Hall 1", "123", Instant.now().getEpochSecond(), true, "A", 154.34, new BigDecimal("43.9"));
        System.out.println(fullTicket);

        Ticket fullTicketWithAutomaticallyTime = new Ticket("2", "Hall 2", "002", false, "B", 154.35, new BigDecimal("222"));
        System.out.println(fullTicketWithAutomaticallyTime);

        Ticket limitedTicket = new Ticket("Hall 2", "324", Instant.now().getEpochSecond());
        System.out.println(limitedTicket);

        Ticket limitedTicketWithAutomaticallyTime = new Ticket("Hall 3", "364");
        System.out.println(limitedTicketWithAutomaticallyTime);
    }
}
