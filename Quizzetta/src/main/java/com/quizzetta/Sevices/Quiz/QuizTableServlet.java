package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Model.TakenQuiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("") TODO
public class  QuizTableServlet extends HttpServlet {

    private static final int NUM_OF_QUIZZES_IN_CATEGORY = 5;

    private Connection getConnection(HttpServletRequest req) {
        return (Connection) req.getServletContext().getAttribute("dataBaseConn");
    }

    private QuizDAO getQuizDao(HttpServletRequest req) {
        return  (QuizDAO) req.getSession().getAttribute("QuizDAO");
    }

    private UserDAO getUserDAO(HttpServletRequest req) {
        return  (UserDAO) req.getSession().getAttribute("UserDAO");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = getConnection(req);
        QuizDAO quizDAO = getQuizDao(req);
        UserDAO userDAO = getUserDAO(req);
        long userId = (long) req.getSession().getAttribute("userId");
        List<Quiz> popularQuizzes = getPopularQuizzes(quizDAO);
        List<Quiz> recentQuizzes = getRecentlyCreatedQuizzes(quizDAO);
        List<Quiz> usersRecentQuizzes = getUsersRecentQuizzes(quizDAO, userId);
        List<Quiz> recentQuizzesTakenByUser = getRecentlyTakenQuizzes(quizDAO, userDAO, userId);

    }

    private List<Quiz> getPopularQuizzes(QuizDAO quizDao) {
        List<Quiz> result = quizDao.getMostPopularQuizzes();
        if (result.size() > NUM_OF_QUIZZES_IN_CATEGORY) result.subList(NUM_OF_QUIZZES_IN_CATEGORY, result.size()).clear();
        return result;
    }

    private List<Quiz> getRecentlyCreatedQuizzes(QuizDAO quizDao) {
        List<Quiz> result = quizDao.getRecentlyCreatedQuizzes();
        if (result.size() > NUM_OF_QUIZZES_IN_CATEGORY) result.subList(NUM_OF_QUIZZES_IN_CATEGORY, result.size()).clear();
        return result;
    }

    private List<Quiz> getUsersRecentQuizzes(QuizDAO quizDao, Long userId) {
        List<Quiz> result = quizDao.getRecentlyCreatedQuizzes(userId);
        if (result.size() > NUM_OF_QUIZZES_IN_CATEGORY) result.subList(NUM_OF_QUIZZES_IN_CATEGORY, result.size()).clear();
        return result;
    }

    private List<Quiz> getRecentlyTakenQuizzes(QuizDAO quizDao, UserDAO userDao, long userId){
        List<TakenQuiz> takenQuizzes = userDao.getRecentlyTakenQuizzes(userId);
        List<Quiz> result = new ArrayList<>();
        for (TakenQuiz currQuiz : takenQuizzes) {
            long id = currQuiz.getQuizId();
            result.add(quizDao.getQuiz(id));
        }
        if (result.size() > NUM_OF_QUIZZES_IN_CATEGORY) result.subList(NUM_OF_QUIZZES_IN_CATEGORY, result.size()).clear();
        return result;
    }
}