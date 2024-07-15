package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Ticket;
import org.example.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") int id) {
        return ticketService.fetchTicketById(id);
    }
}
