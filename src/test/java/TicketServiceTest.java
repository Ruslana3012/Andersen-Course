import org.example.entity.Ticket;
import org.example.entity.TicketType;
import org.example.repository.TicketRepository;
import org.example.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @InjectMocks
    private TicketServiceImpl ticketService;
    @Mock
    private TicketRepository ticketRepository;
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = mock(Ticket.class);
    }

    @Test
    @DisplayName("Should save new ticket")
    public void saveTicketPositiveTest() {
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        ticketService.saveTicket(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    @DisplayName("Should throw exception when ticket is null")
    public void saveTicketNegativeTest() {
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            ticketService.saveTicket(null);
        });
    }

    @Test
    @DisplayName("Should throw exception when ticket is empty")
    public void saveTicketCornerTest() {
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenThrow(new DataIntegrityViolationException(""));

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            ticketService.saveTicket(new Ticket());
        });
    }

    @Test
    @DisplayName("Should get all tickets")
    void getAllTicketsPositiveTest() {
        Mockito.when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<Ticket> actualTickets = ticketService.getAllTickets();

        Assertions.assertEquals(List.of(ticket), actualTickets);
    }

    @Test
    @DisplayName("Should throw exception when the returned list is null")
    void getAllTicketsNegativeTest() {
        Mockito.when(ticketRepository.findAll()).thenReturn(null);

        List<Ticket> actualTickets = ticketService.getAllTickets();

        assertNull(actualTickets);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return an empty list")
    void getAllTicketsCornerTest() {
        Mockito.when(ticketRepository.findAll()).thenReturn(Collections.emptyList());

        List<Ticket> actualTickets = ticketService.getAllTickets();

        assertNotNull(actualTickets);
        assertTrue(actualTickets.isEmpty());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should fetch ticket by id")
    void fetchTicketByIdPositiveTest() {
        Mockito.when(ticketRepository.getTicketById(1)).thenReturn(ticket);

        Ticket actualTicket = ticketService.fetchTicketById(1);

        Assertions.assertEquals(ticket, actualTicket);
        verify(ticketRepository, times(1)).getTicketById(1);
    }

    @Test
    @DisplayName("Should return null when ticket wasn't find by id")
    void fetchTicketByIdNegativeTest() {
        Mockito.when(ticketRepository.getTicketById(1)).thenReturn(null);

        Ticket actualTicket = ticketService.fetchTicketById(1);

        Assertions.assertNull(actualTicket);
        verify(ticketRepository, times(1)).getTicketById(1);
    }

    @Test
    @DisplayName("Should return null when id is a negative")
    void fetchTicketByIdCornerTest() {
        Mockito.when(ticketRepository.getTicketById(-1)).thenReturn(null);

        Ticket actualTicket = ticketService.fetchTicketById(-1);

        Assertions.assertNull(actualTicket);
        verify(ticketRepository, times(1)).getTicketById(-1);
    }

    @Test
    @DisplayName("Should fetch tickets by user_id")
    void fetchTicketsByUserIdPositiveTest() {
        Mockito.when(ticketRepository.getTicketsByUserId(1)).thenReturn(List.of(ticket));

        List<Ticket> actualTickets = ticketService.fetchTicketsByUserId(1);

        Assertions.assertEquals(List.of(ticket), actualTickets);
        verify(ticketRepository, times(1)).getTicketsByUserId(1);
    }

    @Test
    @DisplayName("Should return null when ticket wasn't find by user_id")
    void fetchTicketsByUserIdNegativeTest() {
        Mockito.when(ticketRepository.getTicketsByUserId(1)).thenReturn(null);

        List<Ticket> actualTickets = ticketService.fetchTicketsByUserId(1);

        Assertions.assertNull(actualTickets);
        verify(ticketRepository, times(1)).getTicketsByUserId(1);
    }

    @Test
    @DisplayName("Should return null when user_id is less than 0")
    void fetchTicketsByUserIdCornerTest() {
        Mockito.when(ticketRepository.getTicketsByUserId(-1)).thenReturn(null);

        List<Ticket> actualTickets = ticketService.fetchTicketsByUserId(-1);

        Assertions.assertNull(actualTickets);
        verify(ticketRepository, times(1)).getTicketsByUserId(-1);
    }

    @Test
    @DisplayName("Should update ticket type")
    void updateTicketTypePositiveTest() {
        Mockito.when(ticketRepository.getTicketById(1)).thenReturn(ticket);
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        ticket.setTicketType(TicketType.YEAR);
        Ticket newTicket = ticketService.updateTicketType(1, ticket);

        assertEquals(ticket, newTicket);
        verify(ticketRepository, times(1)).getTicketById(1);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    @DisplayName("Should throw exception when no ticket is found for the provided id")
    void updateTicketTypeNegativeTest() {
        Mockito.when(ticketRepository.getTicketById(1)).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            ticketService.updateTicketType(1, ticket);
        });

        Mockito.verify(ticketRepository, Mockito.times(1)).getTicketById(1);
        Mockito.verify(ticketRepository, Mockito.times(0)).save(any(Ticket.class));
    }

    @Test
    @DisplayName("Should throw exception when new ticket is null")
    void updateTicketTypeCornerTest() {
        Mockito.when(ticketRepository.getTicketById(1)).thenReturn(new Ticket());

        Assertions.assertThrows(NullPointerException.class, () -> {
            ticketService.updateTicketType(1, null);
        });

        Mockito.verify(ticketRepository, times(1)).getTicketById(1);
    }

    @Test
    @DisplayName("Should delete ticket by user_id")
    void deleteTicketByUserIdPositiveTest() {
        ticketService.deleteTicketByUserId(1);

        Mockito.verify(ticketRepository, times(1)).deleteTicketByUserId(1);
    }
}
