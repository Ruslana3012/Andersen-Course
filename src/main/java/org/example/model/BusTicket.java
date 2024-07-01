package org.example.model;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusTicket {
    private Long id;
    private String ticketClass;
    private String ticketType;
    private Date date;
    private int price;

    public BusTicket(Long id, Date date, String ticketClass, int price) {
    }
}