package Model;

import DAOs.UserDAO;
import junit.framework.TestCase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestUserDAO extends TestCase {

    private UserDAO dao;
    private User user1;
    private User user2;


    public void setUp () throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {
        DatabaseConnector.resetTables();
        user1 = new User("test1", new Hash("pass").generateHash(), "testName1", "testLastName1", false);
        user2 = new User("test2", new Hash("pass").generateHash(), "testName2", "testLastName2", false);
        dao = new UserDAO(DatabaseConnector.getConnection());
        dao.addUser(user1);
        dao.addUser(user2);
    }

//    public void testAdd() throws SQLException {
//        User temp = dao.getUser(user1.getId());
//        assertEquals(temp.getFirstName(), user1.getFirstName());
//        assertEquals(temp.getLastName(), user1.getLastName());
//        assertEquals(temp.getPasswordHash(), user1.getPasswordHash());
//        assertEquals(temp.getUsername(), user1.getUsername());
//    }
}
