package org.example.dao;

import org.example.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceDAO implements AutoCloseable {
    private Connection connection;

    public UserServiceDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveUser(UserEntity user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name) VALUES (?)")) {
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> getAllUsers() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            List<UserEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setCreationDate(resultSet.getTimestamp("creation_date"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity fetchUserById(int userId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setCreationDate(resultSet.getTimestamp("creation_date"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserIdByName(String name) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new RuntimeException("User with name " + name + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserByIdANdAllTheirTickets(int userId) {
        Savepoint savepoint = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();

            TicketServiceDAO ticketServiceDAO = new TicketServiceDAO(this.connection);
            ticketServiceDAO.deleteTicketByUserId(userId);

            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
                statement.setInt(1, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            connection.commit();
        } catch (SQLException e) {
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    connection.commit();
                } catch (SQLException exception) {
                    throw new RuntimeException("Error rolling back transaction", exception);
                }
            }
            throw new RuntimeException("Error performing deletions", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Couldn't reset auto commit", e);
            }
        }

    }

    public void updateUserAndTicket(int userId, String newUserName, int ticketId, String newTicketType) throws SQLException {
        Savepoint savepoint = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("Savepoint1");

            String sqlUpdateUser = "UPDATE users SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlUpdateUser)) {
                statement.setString(1, newUserName);
                statement.setInt(2, userId);
                statement.executeUpdate();
            }

            String sqlUpdateTicket = "UPDATE tickets SET ticket_type = ?::ticket_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlUpdateTicket)) {
                statement.setString(1, newTicketType);
                statement.setInt(2, ticketId);
                statement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            if (savepoint != null) {
                connection.rollback(savepoint);
                connection.commit();
            } else {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
