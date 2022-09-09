package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Validator.QuizValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SHEMOSVLA POSTHI");
        String name = (String) req.getParameter("quizName");
//        long userId = (long) req.getSession().getAttribute("userId");

        System.out.println("AI AQ");

        boolean isRandomizedOrder = req.getParameter("randomQuestionsBox") != null;
        boolean isOnePage = req.getParameter("onePageButton") != null;
        boolean immediateFeedback = req.getParameter("immediateFeedbackBox") != null;
        boolean isPracticeMode = req.getParameter("practiceModeBox") != null;

        String questionCount = req.getParameter("numberOfQuestions");

        System.out.println(name);
        System.out.println(isRandomizedOrder);
        System.out.println(isOnePage);
        System.out.println(immediateFeedback);
        System.out.println(isPracticeMode);

        QuizDAO quizDAO = (QuizDAO) req.getServletContext().getAttribute("QuizDAO");
        Quiz quiz = new Quiz(name, 10L, isRandomizedOrder, isOnePage, immediateFeedback, isPracticeMode);
        QuizValidator quizValidator = new QuizValidator(questionCount);

        if (!quizValidator.validate()) {
            List<ValidationError> errors = quizValidator.getErrors();
            req.getSession().setAttribute("QuizErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("CreateQuiz.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("quizName", name);
        req.getSession().setAttribute("totalQuestionCount", Integer.valueOf(questionCount));
        req.getSession().setAttribute("quiz", quiz);
        req.getSession().setAttribute("questionCount", 0);
        req.getSession().setAttribute("numberOfQuestions", questionCount);
        quizDAO.addQuiz(quiz);
        req.getRequestDispatcher("Questions.jsp").forward(req, resp);


//        long creatorUserId, boolean randomQuestions, boolean onePage,
//        boolean immediateFeedback, boolean practiceMode


    }


}
