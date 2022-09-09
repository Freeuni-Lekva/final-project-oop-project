package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.MultipleChoiceQuestionDAO;
import com.quizzetta.DAOs.PictureResponseQuestionDAO;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Validator.PictureQuestionValidator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PictureResponseServlet")
public class AddPictureResponseQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionText = req.getParameter("question");
        String imageURL = req.getParameter("imageURL");
        String answerText = req.getParameter("answer");


        Quiz quiz = (Quiz) req.getSession().getAttribute("quiz");
        long quizId = quiz.getId();

        Question question = new Question(questionText, quizId, imageURL, 1);
        PictureResponseQuestionDAO questionDAO = (PictureResponseQuestionDAO) req.getServletContext().getAttribute("PictureResponseQuestionDAO");

        PictureQuestionValidator validator = new PictureQuestionValidator(imageURL, questionText, answerText);

        if (!validator.validate()) {
            List<ValidationError> errors = validator.getErrors();

            for (ValidationError v : errors) {
                System.out.println(v.getErrorMessage());
            }

            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("PictureResponseQuestion.jsp").forward(req, resp);
            return;
        }

        questionDAO.addQuestion(question, quizId);

        Answer answer = new Answer(answerText, question.getId(), true);
        questionDAO.addAnswer(answer, question.getId());

        req.getSession().setAttribute("questionCount", (int) req.getSession().getAttribute("questionCount") + 1);


        if ((int) req.getSession().getAttribute("questionCount") == Integer.parseInt((String) req.getSession().getAttribute("numberOfQuestions"))) {
            req.getRequestDispatcher("QuizCreated.jsp").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("Questions.jsp").forward(req, resp);
            return;
        }


    }
}
