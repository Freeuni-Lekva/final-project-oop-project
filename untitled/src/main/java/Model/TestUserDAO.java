package Model;

import DAOs.UserDAO;
import junit.framework.TestCase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class TestUserDAO extends TestCase {

    private UserDAO dao;
    private User user1;
    private User user2;
    private User user3;
    private User user4;


    public void setUp () throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {
        DatabaseConnector.resetTables();
        user1 = new User("kakhv20", new Hash("pass").generateHash(), "Kakha",
                         "Akhvlediani", false);
        user2 = new User("malkh19", new Hash("pass").generateHash(), "Misho",
                         "Alkhanashvili", false);
        user3 = new User("tbark19", new Hash("pass").generateHash(), "Temuka",
                         "Barkaia", false);
        user4 = new User("vmama20", new Hash("pass").generateHash(), "Vajha",
                         "Mamatsashvili", false);
        dao = new UserDAO(DatabaseConnector.getConnection());
        dao.addUser(user1);
        dao.addUser(user2);
        dao.addUser(user3);
        dao.addUser(user4);
    }

    public void testAddAndGet() throws SQLException {
        User temp = dao.getUser(user1.getId());
        assertEquals(temp.getId(), user1.getId());
        assertEquals(temp.getFirstName(), user1.getFirstName());
        assertEquals(temp.getLastName(), user1.getLastName());
        assertEquals(temp.getPasswordHash(), user1.getPasswordHash());
        assertEquals(temp.getUsername(), user1.getUsername());
        assertEquals(temp.isAdmin(), user1.isAdmin());
    }

    public void testFriends() throws SQLException {
        dao.addFriend(user1.getId(), user2.getId());
        dao.addFriend(user1.getId(), user3.getId());
        dao.addFriend(user1.getId(), user4.getId());
        List<User> user1Friends = dao.getFriends(user1.getId());
        assertEquals(user1Friends.size(), 3);
        assertEquals(user1Friends.get(0).getUsername(), "malkh19");
        assertEquals(user1Friends.get(1).getUsername(), "tbark19");
        assertEquals(user1Friends.get(2).getUsername(), "vmama20");
    }

    public void testQuizHistory() throws SQLException {

    }
}
