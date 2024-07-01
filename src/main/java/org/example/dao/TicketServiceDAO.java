package org.example.dao;

import org.example.entity.TicketEntity;
import org.example.entity.TicketType;
import org.example.entity.UserEntity;

import java.sql.*;

public class TicketServiceDAO {
    public void saveTicket(TicketEntity ticket) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tickets (ticket_type, user_id) VALUES (?, ?)");
            statement.setObject(1, ticket.getTicketType());
            statement.setInt(2, ticket.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TicketEntity fetchTicketById(int id) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TicketEntity ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setUserId(resultSet.getInt("user_id"));
                return ticket;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TicketEntity fetchTicketByUserId(int userId) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TicketEntity ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setUserId(resultSet.getInt("user_id"));
                return ticket;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TicketEntity updateTicketType(int ticketId, TicketEntity newTicket) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("UPDATE tickets SET ticket_type = ? WHERE id = ?");
            statement.setObject(1, newTicket.getTicketType());
            statement.setInt(2, ticketId);
            statement.executeUpdate();
            return newTicket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTicketByUserId(int userId) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE user_id = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
