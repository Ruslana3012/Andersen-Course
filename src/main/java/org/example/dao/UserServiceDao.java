package org.example.dao;

import org.example.entity.UserEntity;

import java.sql.*;

public class UserServiceDao {
    public void saveUser(UserEntity user) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name) VALUES (?)");
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity fetchUserById(int userId) {
        try {
            Connection connection = DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserByIdANdAllTheirTickets(int userId) {
        try {
            Connection connection =  DriverManager.getConnection("", "postgres", "vetabe");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();

            TicketServiceDAO ticketServiceDAO = new TicketServiceDAO();
            ticketServiceDAO.deleteTicketByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
