package com.quizzetta;
import com.quizzetta.DAOs.*;
import com.quizzetta.Model.DatabaseConnector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    // TODO ADMIN NEEDS TO BE ADDED OR CHANGED
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        servletContextEvent.getServletContext().setAttribute("dataBaseConn", conn); // TODO CHECK WITH khanju

        UserDAO userDAO = new UserDAO(conn);
        QuizDAO quizDAO = new QuizDAO(conn);

        StandardTextQuestionDAO standardTextQuestionDAO = new StandardTextQuestionDAO(conn);
        MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO(conn);
        PictureResponseQuestionDAO pictureResponseQuestionDAO = new PictureResponseQuestionDAO(conn);
        FillTheBlankQuestionDAO fillTheBlankQuestionDAO = new FillTheBlankQuestionDAO(conn);

        NoteDAO noteDAO = new NoteDAO(conn);

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO(conn);

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
