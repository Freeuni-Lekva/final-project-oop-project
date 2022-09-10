package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class QuizTableServlet extends HttpServlet {

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

        int numOfQuizzesInEachCategory = 10; // TODO WHERE SHOULD WE PUT IT

        List<Quiz> allQuizzes = quizDAO.getAllQuizzes();

        List<Quiz> popularQuizzes = getPopularQuizzes(allQuizzes, NUM_OF_QUIZZES_IN_CATEGORY);
        List<Quiz> recentQuizzes = getRecentlyCreatedQuizzes(allQuizzes, NUM_OF_QUIZZES_IN_CATEGORY);

//        List<Quiz> recentQuizzesTakenByUser = getRecentlyTakenQuizzes(userDAO.)

    }

    private List<Quiz> getPopularQuizzes(List<Quiz> allQuizzes, int numOfQuizzesInEachCategory) { // TODO REVIEW
        List<Quiz> result = new ArrayList<>();

        for (int i = 0; i < allQuizzes.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if (allQuizzes.get(i).getNumberOfUses() > result.get(j).getNumberOfUses()) {
                    result.add(j, allQuizzes.get(i));
                } else if (result.size() < numOfQuizzesInEachCategory) {
                    result.add(allQuizzes.get(i));
                }
            }
        }

        for (int i = result.size() - 1; i >= numOfQuizzesInEachCategory ; i--) {
            result.remove(i);
        }

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

    private List<Quiz> getRecentlyTakenQuizzes(List<Quiz> allQuizzes, int numOfQuizzesInEachCategory) {
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