package org.example.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class TicketEntity {
    private int id;
    private TicketType ticketType;
    private Timestamp creationDate;
    private int userId;

    public TicketEntity(TicketType ticketType, Timestamp creationDate, int userId) {
        this.ticketType = ticketType;
        this.creationDate = creationDate;
        this.userId = userId;
    }
}
