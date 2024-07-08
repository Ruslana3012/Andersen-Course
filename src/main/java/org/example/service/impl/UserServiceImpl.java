package org.example.service.impl;

import org.example.entity.Ticket;
import org.example.entity.TicketType;
import org.example.entity.User;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public UserServiceImpl(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User fetchUserById(long userId) {
        return userRepository.getUserById(userId);
    }

    @Transactional
    public void deleteUserByIdANdAllTheirTickets(long userId) {
        List<Ticket> tickets = ticketRepository.getTicketsByUserId(userId);
        ticketRepository.deleteAll(tickets);

        userRepository.deleteById(userId);

    }

    @Transactional
    public void updateUserAndTicket(long userId, String newUserName, long ticketId, String newTicketType) {
        User newUser = userRepository.getUserById(userId);
        newUser.setName(newUserName);

        Ticket newTicket = ticketRepository.getTicketById(ticketId);
        newTicket.setTicketType(TicketType.valueOf(newTicketType));

        userRepository.save(newUser);
        ticketRepository.save(newTicket);
    }
}
