package com.quizzetta;
import com.quizzetta.DAOs.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ContextListener implements ServletContextListener {

    // TODO ADMIN NEEDS TO BE ADDED OR CHANGED
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Connection dataBaseConn = (Connection) servletContextEvent.getServletContext().getAttribute("dataBaseConn"); // TODO CHECK WITH khanju

        UserDAO userDAO = new UserDAO(dataBaseConn);
        QuizDAO quizDAO = new QuizDAO(dataBaseConn);

        StandardTextQuestionDAO standardTextQuestionDAO = new StandardTextQuestionDAO(dataBaseConn);
        MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO(dataBaseConn);
        PictureResponseQuestionDAO pictureResponseQuestionDAO = new PictureResponseQuestionDAO(dataBaseConn);
        FillTheBlankQuestionDAO fillTheBlankQuestionDAO = new FillTheBlankQuestionDAO(dataBaseConn);

        NoteDAO noteDAO = new NoteDAO(dataBaseConn);

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO(dataBaseConn);

        // Sets
        servletContextEvent.getServletContext().setAttribute("UserDAO", userDAO);
        servletContextEvent.getServletContext().setAttribute("QuizDAO", quizDAO);

        servletContextEvent.getServletContext().setAttribute("StandardTextQuestionDAO", standardTextQuestionDAO);
        servletContextEvent.getServletContext().setAttribute("MultipleChoiceQuestionDAO", multipleChoiceQuestionDAO);
        servletContextEvent.getServletContext().setAttribute("PictureResponseQuestionDAO", pictureResponseQuestionDAO);
        servletContextEvent.getServletContext().setAttribute("FillTheBlankQuestionDAO", fillTheBlankQuestionDAO);
        servletContextEvent.getServletContext().setAttribute("NoteDAO", noteDAO);
        servletContextEvent.getServletContext().setAttribute("FriendRequestDAO", friendRequestDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
