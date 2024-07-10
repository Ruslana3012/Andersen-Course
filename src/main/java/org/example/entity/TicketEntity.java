package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "user_id")
    private int userId;

    public TicketEntity(TicketType ticketType, Timestamp creationDate, int userId) {
        this.ticketType = ticketType;
        this.creationDate = creationDate;
        this.userId = userId;
    }
}
