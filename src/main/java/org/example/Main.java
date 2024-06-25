package org.example;

import org.example.mode.BusTicket;
import org.example.service.BusTicketService;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BusTicketService.storeTicket(new BusTicket(1L, new Date(), "A", 356));
        BusTicketService.storeTicket(new BusTicket(2L, new Date(), "B", 216));
        BusTicketService.storeTicket(new BusTicket(3L, new Date(), "B", 3308));
        BusTicketService.storeTicket(new BusTicket(4L, new Date(), "A", 500));
        BusTicketService.storeTicket(new BusTicket(5L, new Date(), "C", 190));

        BusTicket ticket = BusTicketService.getTicketById(2L);
        System.out.println("We anticipate the ticket with id [2]: " + ticket);

        List<BusTicket> tickets = BusTicketService.getTicketsByTypeAndPrice("A", 200, 500);
        System.out.println("We anticipate the tickets with id [1] and [4] " + tickets);

        BusTicketService.removeTicketById(3L);
    }
}