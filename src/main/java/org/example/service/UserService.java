package org.example.service;

import org.example.entity.Ticket;
import org.example.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> getAllUsers();
    User fetchUserById(long userId);
    void deleteUserByIdANdAllTheirTickets(long userId);
    void updateUserAndTicket(long userId, String newUserName, long ticketId, String newTicketType);
    void updateUserAndCreateTicket(long userId, String newUserName, Ticket newTicket);
}
