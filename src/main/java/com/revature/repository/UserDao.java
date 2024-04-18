package com.revature.repository;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

import java.sql.*;

public class UserDao {

    public User getUserByUsername(String username) {
        // TODO: implement
        String sql = "SELECT * FROM users WHERE username = ?";
        User retrievedUser = new User();


        try (Connection conn = ConnectionUtil.createConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                retrievedUser.setId(resultSet.getInt(1));
                retrievedUser.setUsername(resultSet.getString(2));
                retrievedUser.setPassword(resultSet.getString(3));
            }

            return retrievedUser;

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            return null;
        }

    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        // TODO: implement
        try (Connection connection = ConnectionUtil.createConnection()) {
            // craft initial sql
            String sql = "INSERT INTO users (username, password) VALUES (?,?)";
            // create preppared statement
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // the second argument tells the database to return the id that is generated for the new record
            // inject information into our sql statement, order is determined by sql
            ps.setString(1, registerRequest.getUsername());
            ps.setString(2, registerRequest.getPassword());
            // to execute our sql statement AND get the id generated for the new user we have a two-step process
            // step one: call executeUpdate (use this method for "INSERT", "UPDATE" and "DELETE" queries because
            // it will tell you how many rows are affected by your query
            ps.executeUpdate();
            // once the query is executed we can use the getGeneratedKeys method to get the id of the newly
            // created user
            ResultSet rs = ps.getGeneratedKeys();
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            if (rs.next()) {
                // since we are only returning the generated id, we can get it by referencing column 1
                newUser.setId(rs.getInt(1));
            }
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}