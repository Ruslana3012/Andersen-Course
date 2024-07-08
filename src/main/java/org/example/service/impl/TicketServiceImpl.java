package org.example.service.impl;

import org.example.entity.Ticket;
import org.example.repository.TicketRepository;
import org.example.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket fetchTicketById(long id) {
        return ticketRepository.getTicketById(id);
    }

    public List<Ticket> fetchTicketsByUserId(long userId) {
        return ticketRepository.getTicketsByUserId(userId);
    }

    public Ticket updateTicketType(long ticketId, Ticket newTicket) {
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }
        ticket.setTicketType(newTicket.getTicketType());

        return ticketRepository.save(ticket);
    }

    public void deleteTicketByUserId(long userId) {
        ticketRepository.deleteTicketByUserId(userId);
    }
}
