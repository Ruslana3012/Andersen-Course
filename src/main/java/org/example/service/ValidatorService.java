package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.BusTicket;
import org.example.model.Violation;

import java.time.LocalDate;
import java.util.List;

import static org.example.service.ViolationService.putViolationInMap;

@Slf4j
public class ValidatorService {
    private static int validTickets = 0;

    public static void validateTickets(List<BusTicket> tickets) {
        tickets.forEach(busTicket -> {
            boolean isValidTicketType = isValidTicketType(busTicket);
            boolean isValidStartDate = isValidStartDateInTicket(busTicket);
            boolean isValidPrice = isValidPriceInTicket(busTicket);

            if (isValidTicketType && isValidStartDate && isValidPrice) {
                validTickets++;
            } else {
                System.out.println();
            }
        });
    }

    public static int getTheCountOfValidTickets() {
        return validTickets;
    }

    private static boolean isValidStartDateInTicket(BusTicket busTicket) {
        LocalDate localDate = LocalDate.now();
        if (busTicket.getStartDate() == null || busTicket.getStartDate().isEmpty()) {
            log.error("Start date can't be null or empty: " + busTicket);
            putViolationInMap(Violation.START_DATE);
            return false;
        } else if (localDate.isBefore(LocalDate.parse(busTicket.getStartDate()))) {
            log.error("Start date can't be in the future: " + busTicket);
            putViolationInMap(Violation.START_DATE);
            return false;
        }
        return true;
    }

    private static boolean isValidPriceInTicket(BusTicket busTicket) {
        if (busTicket.getPrice() == null || busTicket.getPrice().isEmpty()) {
            log.error("Price can't be null or empty: " + busTicket);
            putViolationInMap(Violation.PRICE);
            return false;
        } else {
            int priceInt = Integer.parseInt(busTicket.getPrice());
            if (priceInt == 0) {
                log.error("Price can't be 0: " + busTicket);
                putViolationInMap(Violation.PRICE);
                return false;
            }
        }
        return true;
    }

    private static boolean isValidTicketType(BusTicket busTicket) {
        if (busTicket.getTicketType() == null) {
            log.error("Ticket type can't be null: " + busTicket);
            putViolationInMap(Violation.TICKET_TYPE);
            return false;
        } else if (!List.of("DAY", "WEEK", "MONTH", "YEAR").contains(busTicket.getTicketType())) {
            log.error("Invalid ticket type, valid values are 'DAY', 'WEEK', 'MONTH', 'YEAR': " + busTicket);
            putViolationInMap(Violation.TICKET_TYPE);
            return false;
        }
        return true;
    }
}