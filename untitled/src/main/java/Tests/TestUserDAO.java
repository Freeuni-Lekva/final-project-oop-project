package Tests;

import DAOs.QuizDAO;
import DAOs.UserDAO;
import Model.*;
import junit.framework.TestCase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TestUserDAO extends TestCase {

    private UserDAO dao;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private QuizDAO quizDao;


    public void setUp () throws SQLException, IOException, ClassNotFoundException {
        DatabaseConnector.resetTables();
        user1 = new User("kakhv20", Hasher.generateHash("pass"), "Kakha",
                         "Akhvlediani", false);
        user2 = new User("malkh19", Hasher.generateHash("pass"), "Misho",
                         "Alkhanashvili", false);
        user3 = new User("tbark19", Hasher.generateHash("pass"), "Temuka",
                         "Barkaia", false);
        user4 = new User("vmama20", Hasher.generateHash("pass"), "Vajha",
                         "Mamatsashvili", false);
        Connection myConn = DatabaseConnector.getConnection();
        dao = new UserDAO(myConn);
        quizDao = new QuizDAO(myConn);
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
        Quiz quiz1 = new Quiz("Quiz#1", 1, false, false,
                              false, false);
        quizDao.addQuiz(quiz1);
        Quiz quiz2 = new Quiz("Quiz#2", 1, false, false,
                              false, false);
        quizDao.addQuiz(quiz2);
        Quiz quiz3 = new Quiz("Quiz#3", 1, false, false,
                              false, false);
        quizDao.addQuiz(quiz3);
        Quiz quiz4 = new Quiz("Quiz#4", 2, false, false,
                              false, false);
        quizDao.addQuiz(quiz4);
        TakenQuiz tquiz1 = new TakenQuiz(user1.getId(), quiz1.getId(), 100,
                           new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        TakenQuiz tquiz2 = new TakenQuiz(user1.getId(), quiz2.getId(), 100,
                           new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        TakenQuiz tquiz3 = new TakenQuiz(user1.getId(), quiz3.getId(), 100,
                           new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        TakenQuiz tquiz4 = new TakenQuiz(user1.getId(), quiz4.getId(), 100,
                           new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        dao.takeTheQuiz(tquiz1);
        dao.takeTheQuiz(tquiz2);
        dao.takeTheQuiz(tquiz3);
        dao.takeTheQuiz(tquiz4);
        List<TakenQuiz> takenQuizzes = dao.getQuizHistory(user1.getId());
        assertEquals(4, takenQuizzes.size());
        assertEquals(tquiz1.getQuizId(), takenQuizzes.get(0).getQuizId());
        assertEquals(tquiz2.getQuizId(), takenQuizzes.get(1).getQuizId());
        assertEquals(tquiz3.getQuizId(), takenQuizzes.get(2).getQuizId());
        assertEquals(tquiz4.getQuizId(), takenQuizzes.get(3).getQuizId());
    }

    public void testAdmin() throws SQLException {
        dao.makeAdmin(user1.getId());
        assertTrue(dao.getUser(user1.getId()).isAdmin());
        dao.deleteAdmin(user1.getId());
        assertFalse(dao.getUser(user1.getId()).isAdmin());
    }
}
