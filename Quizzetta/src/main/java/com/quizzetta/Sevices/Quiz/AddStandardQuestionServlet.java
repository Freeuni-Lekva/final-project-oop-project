package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.StandardTextQuestionDAO;
import com.quizzetta.Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddStandardQuestion")
public class AddStandardQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionInput = req.getParameter("question");
        String answerInput = req.getParameter("answer");

        System.out.println(questionInput);
        System.out.println(answerInput);

        Quiz quiz = (Quiz) req.getSession().getAttribute("quiz");
//        Quiz quiz =  req.getServletContext().getAttribute("quizDAO")
        Long quizId = quiz.getId();

//        StandardTextQuestionDAO1 questionDAO = (StandardTextQuestionDAO1) req.getServletContext().getAttribute("StandardTextQuestionDAO1");
//        StandardAnswer1 answer1 = new StandardAnswer1(answerInput);
//        StandardQuestion1 question1 = new StandardQuestion1(questionInput, answer1, quizId);
//
//        questionDAO.addQuestion(question1);

        StandardTextQuestionDAO questionDAO = (StandardTextQuestionDAO) req.getServletContext().getAttribute("StandardTextQuestionDAO");

        Question question = new Question(questionInput, quizId, null, 1);
        questionDAO.addQuestion(question, quizId);

        Answer answer = new Answer(answerInput, question.getId(), true);
        questionDAO.addAnswer(answer);

        req.getSession().setAttribute("questionCount", (int) req.getSession().getAttribute("questionCount") + 1);
        System.out.println("QUESTION COUNT:" + req.getSession().getAttribute("questionCount"));

        if ((int) req.getSession().getAttribute("questionCount") == Integer.parseInt((String) req.getSession().getAttribute("numberOfQuestions"))) {
            System.out.println("AQ RATOM AR VART?");
            req.getRequestDispatcher("QuizCreated.jsp").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("Questions.jsp").forward(req, resp);
            return;
        }

//        req.getRequestDispatcher("Questions.jsp").forward(req, resp);
    }
}
