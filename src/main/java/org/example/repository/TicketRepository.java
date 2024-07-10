package org.example.repository;

import org.example.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket getTicketById(long id);
    List<Ticket> getTicketsByUserId(long user_id);
    void deleteTicketByUserId(long user_id);
}
