package org.example.service.impl;

import org.example.model.BusTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusTicketService {
    public static List<BusTicket> busTicketList = new ArrayList<>();

    public static void storeTicket(BusTicket busTicket) {
        busTicketList.add(busTicket);
    }

    public static void removeTicketById(Long id) {
        busTicketList.remove(getTicketById(id));
    }

    public static BusTicket getTicketById(Long id) {
        return busTicketList.stream()
                .filter(busTicket -> busTicket.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("BusTicket with the ID " + id + " not found."));
    }

    /**
     * Retrieves a list of bus tickets, filtered by type and price range.
     * <p>
     * This method traverses through the list of all bus tickets and filters out those that match the specified type and
     * whose prices fall within the provided range, inclusive of fromPrice and toPrice.
     * </p>
     *
     * @param type      ticket type to be filtered for; this is a string indicating the type of the bus ticket
     * @param fromPrice denotes the lower limit of the price range; all tickets cheaper than this value will be excluded
     * @param toPrice   denotes the upper limit of the price range; all tickets more expensive than this value will be excluded
     * @return a list of {@code BusTicket} objects that match the specified criteria; returns an empty list if no tickets match the criteria
     */
    public static List<BusTicket> getTicketsByTypeAndPrice(String type, int fromPrice, int toPrice) {
        return busTicketList.stream()
                .filter(busTicket -> busTicket.getTicketType().equals(type) && busTicket.getPrice() >= fromPrice && busTicket.getPrice() <= toPrice)
                .collect(Collectors.toList());
    }
}
