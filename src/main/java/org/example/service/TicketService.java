package org.example.service;

import org.example.entity.Ticket;

import java.util.List;

public interface TicketService {
    void saveTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    Ticket fetchTicketById(long id);
    List<Ticket> fetchTicketsByUserId(long userId);
    Ticket updateTicketType(long ticketId, Ticket newTicket);
    void deleteTicketByUserId(long userId);
}
