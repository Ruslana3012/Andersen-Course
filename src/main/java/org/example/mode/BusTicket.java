package org.example.mode;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusTicket {
    private Long id;
    private Date date;
    private String type;
    private int price;
}
