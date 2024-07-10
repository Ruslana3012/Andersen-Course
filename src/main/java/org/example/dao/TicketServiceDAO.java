package org.example.dao;

import org.example.entity.TicketEntity;
import org.example.entity.TicketType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceDAO implements AutoCloseable {
    private Connection connection;

    public TicketServiceDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void saveTicket(TicketEntity ticket) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO tickets (ticket_type, user_id) VALUES (?, ?)")) {
            statement.setObject(1, ticket.getTicketType().name(), Types.OTHER);
            statement.setInt(2, ticket.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TicketEntity> getAllTickets() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets")) {
            ResultSet resultSet = statement.executeQuery();
            List<TicketEntity> tickets = new ArrayList<>();
            while (resultSet.next()) {
                TicketEntity ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type").toUpperCase()));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TicketEntity fetchTicketById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TicketEntity ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type").toUpperCase()));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
                return ticket;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TicketEntity> fetchTicketsByUserId(int userId) {
        List<TicketEntity> tickets = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets WHERE user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TicketEntity ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type").toUpperCase()));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public TicketEntity updateTicketType(int ticketId, TicketEntity newTicket) {
        Savepoint savepoint = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("UpdateTicketType");

            try (PreparedStatement statement = connection.prepareStatement("UPDATE tickets SET ticket_type = ? WHERE id = ?")) {
                statement.setObject(1, newTicket.getTicketType().name(), Types.OTHER);
                statement.setInt(2, ticketId);
                statement.executeUpdate();
                connection.commit();
                return newTicket;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException ex) {
                    throw new RuntimeException("Error rolling back transaction", ex);
                }
            }
            throw new RuntimeException("Error performing updating", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Couldn't reset auto commit", e);
            }
        }
    }

    public void deleteTicketByUserId(int userId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE user_id = ?")) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
