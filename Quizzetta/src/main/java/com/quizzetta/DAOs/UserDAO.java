package com.quizzetta.DAOs;

import com.quizzetta.Model.TakenQuiz;
import com.quizzetta.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private final Connection myConn;

    public UserDAO(Connection conn) {
        myConn = conn;
    }

    public void addUser(User user) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("INSERT INTO userTable (username, password_hash," +
                            " first_name, last_name, is_admin) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPasswordHash());
            stm.setString(3, user.getFirstName());
            stm.setString(4, user.getLastName());
            stm.setBoolean(5, user.isAdmin());
            stm.executeUpdate();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long userId = res.getLong(1);
            user.setId(userId);
            System.out.println("NOT SQL EXCEPTION");

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(long userId) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("SELECT * FROM userTable WHERE id = ?");
            stm.setLong(1, userId);
            ResultSet res = stm.executeQuery();
            res.next();
            return new User(res.getLong("id"), res.getString("username"),
                    res.getString("password_hash"), res.getString("first_name"),
                    res.getString("last_name"), res.getBoolean("is_admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User getUser(String userName) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("SELECT * FROM userTable WHERE username = ?");
            stm.setString(1, userName);
            ResultSet res = stm.executeQuery();

            res.next();
            return new User(res.getLong("id"), res.getString("username"),
                    res.getString("password_hash"), res.getString("first_name"),
                    res.getString("last_name"), res.getBoolean("is_admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUser(long userId) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("DELETE FROM userTable WHERE id = ?");
            stm.setLong(1, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFriend(long firstUserId, long secondUserId) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("INSERT INTO friends (first_user_id, second_user_id)" +
                    " VALUES (?, ?)");
            stm.setLong(1, firstUserId);
            stm.setLong(2, secondUserId);
            stm.executeUpdate();

            stm = myConn.prepareStatement("INSERT INTO friends (second_user_id, first_user_id) VALUES (?, ?)");
            stm.setLong(1, firstUserId);
            stm.setLong(2, secondUserId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getFriends(long userId) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("SELECT second_user_id FROM friends" +
                    " WHERE first_user_id = ?");
            stm.setLong(1, userId);
            ResultSet res = stm.executeQuery();
            List<User> friendList = new ArrayList<>();

            while (res.next()) {
                long friendId = res.getLong("second_user_id");
                User currentUser = getUser(friendId);
                friendList.add(currentUser);
            }

            return friendList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFriend(long firstFriendId, long secondFriendId) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("DELETE FROM friends WHERE first_user_id = ? " +
                    "AND second_user_id = ?");
            stm.setLong(1, firstFriendId);
            stm.setLong(2, secondFriendId);
            stm.executeUpdate();
            stm = myConn.prepareStatement("DELETE FROM friends WHERE second_user_id = ? AND first_user_id = ?");
            stm.setLong(1, firstFriendId);
            stm.setLong(2, secondFriendId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeTheQuiz(TakenQuiz quiz) {
        PreparedStatement stm = null;
        try {
            stm = myConn.prepareStatement("INSERT INTO userHistory (user_id, quiz_id, user_score," +
                    "quiz_start_time, quiz_end_time) VALUES (?, ?, ?, ?, ?)");
            stm.setLong(1, quiz.getUserId());
            stm.setLong(2, quiz.getQuizId());
            stm.setDouble(3, quiz.getUserScore());
            stm.setTimestamp(4, quiz.getStartTime());
            stm.setTimestamp(5, quiz.getEndTime());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TakenQuiz> getQuizHistory(long userId) {
        PreparedStatement stm = null;

        try {
            stm = myConn.prepareStatement("SELECT * FROM userHistory WHERE user_id = ?");
            stm.setLong(1, userId);
            ResultSet res = stm.executeQuery();
            List<TakenQuiz> takenQuizzes = new ArrayList<>();
            while (res.next()) {
                TakenQuiz currQuiz = new TakenQuiz(res.getLong("user_id"), res.getLong("quiz_id"),
                        res.getDouble("user_score"),
                        res.getTimestamp("quiz_start_time"),
                        res.getTimestamp("quiz_end_time"));
                takenQuizzes.add(currQuiz);
            }
            return takenQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeAdmin(long userId) {
        PreparedStatement stm = null;

        try {
            stm = myConn.prepareStatement("UPDATE userTable SET is_admin = TRUE WHERE id = ?");
            stm.setLong(1, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdmin(long userId) {
        PreparedStatement stm = null;

        try {
            stm = myConn.prepareStatement("UPDATE userTable SET is_admin = FALSE WHERE id = ?");
            stm.setLong(1, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}