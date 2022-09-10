package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.*;
import com.quizzetta.HelperClasses.Pair;
import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@WebServlet("/TakeQuiz")
public class TakeQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizDAO quizDAO = (QuizDAO) req.getServletContext().getAttribute("QuizDAO");
        long quizId = (long) req.getSession().getAttribute("quizId");;

        Quiz quiz = quizDAO.getQuiz(quizId);
        req.setAttribute("questionList", quiz.getQuestions());
//        req.setAttribute("quizId", quizId);

        req.getRequestDispatcher("TakeQuiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuizDAO quizDAO = (QuizDAO) req.getSession().getAttribute("QuizDAO");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        AnswerDAO[] answerDAO = new AnswerDAO[4];

        answerDAO[0] = (StandardTextQuestionDAO) req.getSession().getAttribute("StandardTextQuestionDAO");
        answerDAO[1] = (FillTheBlankQuestionDAO) req.getSession().getAttribute("FillTheBlankQuestionDAO");
        answerDAO[2] = (MultipleChoiceQuestionDAO) req.getSession().getAttribute("MultipleChoiceQuestionDAO");
        answerDAO[3] = (PictureResponseQuestionDAO) req.getSession().getAttribute("PictureResponseQuestionDAO");


        long userId = (long) req.getSession().getAttribute("userId");
        long quizId = (long) req.getSession().getAttribute("quizId");

        Quiz quiz = quizDAO.getQuiz(quizId);
        List<Question> questionsList = quiz.getQuestions();
        List<Pair<Question, Answer>> questionAnswer;

        long score = 0;

        for (int i = 0; i < questionsList.size(); i++) {

        }

//        score += questionsList.get(i).getS
    }
}
