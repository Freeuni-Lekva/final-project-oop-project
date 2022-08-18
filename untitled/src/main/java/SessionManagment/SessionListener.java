package SessionManagment;

import Quiz.DAOs.*;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;

public class SessionListener implements HttpSessionListener {

    // TODO ADMIN NEEDS TO BE ADDED OR CHANGED
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Connection dataBaseConn = (Connection) se.getSession().getServletContext().getAttribute("dataBaseConn"); // TODO CHECK WITH khanju

        UserDAO userDAO = new UserDAO(dataBaseConn);
        QuizDAO quizDAO = new QuizDAO(dataBaseConn);

        StandardTextQuestionDAO standardTextQuestionDAO = new StandardTextQuestionDAO(dataBaseConn);
        MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO(dataBaseConn);
        PictureResponseQuestionDAO pictureResponseQuestionDAO = new PictureResponseQuestionDAO(dataBaseConn);
        FillTheBlankQuestionDAO fillTheBlankQuestionDAO = new FillTheBlankQuestionDAO(dataBaseConn);

        NoteDAO noteDAO = new NoteDAO(dataBaseConn);

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO(dataBaseConn);

        // Sets
        se.getSession().setAttribute("UserDAO", userDAO);
        se.getSession().setAttribute("QuizDAO", quizDAO);

        se.getSession().setAttribute("StandardTextQuestionDAO", standardTextQuestionDAO);
        se.getSession().setAttribute("MultipleChoiceQuestionDAO", multipleChoiceQuestionDAO);
        se.getSession().setAttribute("PictureResponseQuestionDAO", pictureResponseQuestionDAO);
        se.getSession().setAttribute("FillTheBlankQuestionDAO", fillTheBlankQuestionDAO);
        se.getSession().setAttribute("NoteDAO", noteDAO);
        se.getSession().setAttribute("FriendRequestDAO", friendRequestDAO);
    }

}
