package org.example;

import org.example.model.BusTicket;

import java.util.List;

import static org.example.service.FileService.readFileInList;
import static org.example.service.ValidatorService.getTheCountOfValidTickets;
import static org.example.service.ValidatorService.validateTickets;
import static org.example.service.ViolationService.getMostPopularViolation;

public class Main {
    public static void main(String[] args) {
        List<BusTicket> tickets = readFileInList("src/main/resources/tickets.txt");
        validateTickets(tickets);
        System.out.println("TOTAL TICKETS = " + tickets.size());
        System.out.println("VALID TICKETS = " + getTheCountOfValidTickets());
        System.out.println("MOST POPULAR VIOLATION = " + getMostPopularViolation());
    }
}
