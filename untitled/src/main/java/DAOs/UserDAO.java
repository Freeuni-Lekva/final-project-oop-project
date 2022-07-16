package DAOs;

import Model.Quiz;
import Model.User;

import java.sql.*;
import java.util.List;


public class UserDAO {
    private final Connection myConn;

    public UserDAO(Connection conn) {
        myConn = conn;
    }

    public void addUser (User user) throws SQLException {
        PreparedStatement ps = myConn.prepareStatement("INSERT INTO userTable (username, password_hash, first_name, last_name, is_admin) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPasswordHash());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setBoolean(5, user.isAdmin());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        long id = rs.getLong(1);
        user.setId(id);
    }

    public User getUser (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM userTable WHERE id = ?");
        stm.setLong(1, userId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new User(res.getLong("id"), res.getString("username"), res.getString("password_hash"), res.getString("first_name"), res.getString("last_name"), res.getBoolean("is_admin"));
    }

    public void removeUser (long userId) throws SQLException {
        PreparedStatement ps = myConn.prepareStatement("DELETE FROM userTable WHERE id = ?");
        ps.setLong(1, userId);
        ps.executeUpdate();
    }

    public void addFriend () {}

    public List<User> getFriends () {
        return null;
    }

    public void removeFriend () {}

    public void takeTheQuiz () {}

    public List<Quiz> getQuizHistory () {
        return null;
    }

    public void makeAdmin () {}

    public List<Quiz> getCreatedQuizzes () {
        return null;
    }
}
