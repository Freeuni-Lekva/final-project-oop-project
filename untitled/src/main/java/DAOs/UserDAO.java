package DAOs;

import Model.Quiz;
import Model.TakenQuiz;
import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private final Connection myConn;

    public UserDAO(Connection conn) {
        myConn = conn;
    }

    public void addUser (User user) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO userTable (username, password_hash," +
                                                        "first_name, last_name, is_admin) VALUES (?, ?, ?, ?, ?)",
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
    }

    public User getUser (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM userTable WHERE id = ?");
        stm.setLong(1, userId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new User(res.getLong("id"), res.getString("username"),
                        res.getString("password_hash"), res.getString("first_name"),
                        res.getString("last_name"), res.getBoolean("is_admin"));
    }

    public void removeUser (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM userTable WHERE id = ?");
        stm.setLong(1, userId);
        stm.executeUpdate();
    }

    public void addFriend (long firstUserId, long secondUserId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO friends (first_user_id, second_user_id) VALUES (?, ?)");
        stm.setLong(1, firstUserId);
        stm.setLong(2, secondUserId);
        stm.executeUpdate();
        stm = myConn.prepareStatement("INSERT INTO friends (second_user_id, first_user_id) VALUES (?, ?)");
        stm.setLong(1, firstUserId);
        stm.setLong(2, secondUserId);
        stm.executeUpdate();
    }

    public List<User> getFriends (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT second_user_id FROM friends WHERE first_user_id = ?");
        stm.setLong(1, userId);
        ResultSet res = stm.executeQuery();
        List<User> friendList = new ArrayList<>();
        while (res.next()) {
            long friendId = res.getLong("second_user_id");
            User currentUser = getUser(friendId);
            friendList.add(currentUser);
        }
        return friendList;
    }

    public void removeFriend (long firstFriendId, long secondFriendId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM friends WHERE first_user_id = ? AND second_user_id = ?");
        stm.setLong(1, firstFriendId);
        stm.setLong(2, secondFriendId);
        stm.executeUpdate();
        stm = myConn.prepareStatement("DELETE FROM friends WHERE second_user_id = ? AND first_user_id = ?");
        stm.setLong(1, firstFriendId);
        stm.setLong(2, secondFriendId);
        stm.executeUpdate();
    }

    public void takeTheQuiz (TakenQuiz quiz) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO userHistory (user_id, quiz_id, user_score," +
                                                            "quiz_start_time, quiz_end_time) VALUES (?, ?, ?, ?, ?)");
        stm.setLong(1, quiz.getUserId());
        stm.setLong(2, quiz.getQuizId());
        stm.setDouble(3, quiz.getUserScore());
        stm.setTimestamp(4, quiz.getStartTime());
        stm.setTimestamp(5, quiz.getEndTime());
        stm.executeUpdate();
    }

    public List<TakenQuiz> getQuizHistory (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM userHistory WHERE user_id = ?");
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
    }

    public void makeAdmin (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("UPDATE userTable SET is_admin = TRUE WHERE id = ?");
        stm.setLong(1, userId);
        stm.executeUpdate();
    }

    public List<Quiz> getCreatedQuizzes (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ?");
        stm.setLong(1, userId);
        ResultSet res = stm.executeQuery();
        List<Quiz> createdQuizzes = new ArrayList<>();
        while (res.next()) {
            Quiz currQuiz = new Quiz(res.getLong("id"), res.getString("title"),
                                     res.getLong("creator_id"),
                                     res.getBoolean("are_random_questions"),
                                     res.getBoolean("is_one_page"),
                                     res.getBoolean("is_immediate_feedback"),
                                     res.getBoolean("is_practice_mode"));
            createdQuizzes.add(currQuiz);
        }
        return createdQuizzes;
    }
}
