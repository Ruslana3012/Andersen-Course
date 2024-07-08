package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ColumnTransformer(
            read = "ticket_type::text",
            write = "?::ticket_type"
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", columnDefinition = "ticket_type")
    private TicketType ticketType;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket(TicketType ticketType, Timestamp creationDate, User user) {
        this.ticketType = ticketType;
        this.creationDate = creationDate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                ", userId=" + user.getId() +
                '}';
    }
}
