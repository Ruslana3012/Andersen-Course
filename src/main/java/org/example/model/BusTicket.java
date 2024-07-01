package org.example.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusTicket {
    private String ticketClass;
    String ticketType;
    String startDate;
    String price;
}
