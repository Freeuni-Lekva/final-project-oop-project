package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.Model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizDAO quizDAO = (QuizDAO) req.getServletContext().getAttribute("QuizDAO");
        long quizId = (long) req.getSession().getAttribute("quizId");;

        Quiz quiz = null;
        quiz = quizDAO.getQuiz(quizId);
        req.setAttribute("questionList", quiz.getQuestions());
//        req.setAttribute("quizId", quizId);

        req.getRequestDispatcher("TakeQuiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizDAO quizDAO = (QuizDAO) req.getServletContext().getAttribute("QuizDAO");
        long quizId = Long.parseLong(req.getParameter("quizId"));
        try {
            req.setAttribute("questionsWithAnswers", quizDAO.getQuizQuestionsWithAnswers(quizId));
            req.getRequestDispatcher("Quiz.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        score += questionsList.get(i).getS
    }
}
