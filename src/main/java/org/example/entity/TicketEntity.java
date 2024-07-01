package org.example.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    private int id;
    private TicketType ticketType;
    private Timestamp creationDate;
    private int userId;
}
