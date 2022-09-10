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

        int numOfQuizzesInEachCategory = 10; // TODO WHERE SHOULD WE PUT IT

        List<Quiz> allQuizzes = quizDAO.getAllQuizzes();

        List<Quiz> popularQuizzes = getPopularQuizzes(quizDAO);
        List<Quiz> recentQuizzes = getRecentlyCreatedQuizzes(allQuizzes, NUM_OF_QUIZZES_IN_CATEGORY);

        List<Quiz> recentQuizzesTakenByUser = getRecentlyTakenQuizzes(allQuizzes, userDAO.getQuizHistory(userId), NUM_OF_QUIZZES_IN_CATEGORY);

    }

    private List<Quiz> getPopularQuizzes(QuizDAO quizDao) {
        List<Quiz> result = quizDao.getMostPopularQuizzes();
        if (result.size() > NUM_OF_QUIZZES_IN_CATEGORY) result.subList(NUM_OF_QUIZZES_IN_CATEGORY, result.size());
        return result;
    }

    private List<Quiz> getRecentlyCreatedQuizzes(List<Quiz> allQuizzes, int numOfQuizzesInEachCategory) {
        List<Quiz> result = new ArrayList<>();

        for (Quiz allQuizz : allQuizzes) {
            for (int j = 0; j < result.size(); j++) {
                if (allQuizz.getCreationDate().getTime() > result.get(j).getCreationDate().getTime()) {
                    result.add(j, allQuizz);
                } else if (result.size() < numOfQuizzesInEachCategory) {
                    result.add(allQuizz);
                }
            }
        }

        for (int i = result.size() - 1; i >= numOfQuizzesInEachCategory ; i--) {
            result.remove(i);
        }

        return result;
    }

    private List<Quiz> getRecentlyTakenQuizzes(List<Quiz> allQuizzes, List<TakenQuiz> recentQuizzes, int numOfQuizzesInEachCategory) {
        List<Quiz> result = new ArrayList<>();

        for (Quiz allQuizz : allQuizzes) {
            for (int j = 0; j < result.size(); j++) {
                if (allQuizz.getCreationDate().getTime() > result.get(j).getCreationDate().getTime()) {
                    result.add(j, allQuizz);
                } else if (result.size() < numOfQuizzesInEachCategory) {
                    result.add(allQuizz);
                }
            }
        }

        for (int i = result.size() - 1; i >= numOfQuizzesInEachCategory ; i--) {
            result.remove(i);
        }

        return result;
    }

}