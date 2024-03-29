package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.FillTheBlankQuestionDAO;
import com.quizzetta.DAOs.StandardTextQuestionDAO;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.HelperClasses.HideText;
import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Validator.BlankQuestionValidator;
import com.quizzetta.Validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddBlankQuestion")
public class AddBlankQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionPartOne = req.getParameter("questionPartOne");
        String answerInput = req.getParameter("answer");
        String questionPartTwo = req.getParameter("questionPartTwo");

        System.out.println(questionPartOne);
        System.out.println(answerInput);
        System.out.println(questionPartTwo);

        Quiz quiz = (Quiz) req.getSession().getAttribute("quiz");
        Long quizId = quiz.getId();
        FillTheBlankQuestionDAO questionDAO = (FillTheBlankQuestionDAO) req.getServletContext().getAttribute("FillTheBlankQuestionDAO");

        BlankQuestionValidator validator = new BlankQuestionValidator(questionPartOne, answerInput, questionPartTwo);

        if (!validator.validate()) {
            List<ValidationError> errors = validator.getErrors();
            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("FillBlankQuestion.jsp").forward(req, resp);
            return;
        }






        String hiddenAnswer = HideText.Hide(answerInput, '_');
        String questionText = questionPartOne + " " + hiddenAnswer + " " + questionPartTwo;

        System.out.println(questionText);

        Question question = new Question(questionText, quizId, null, 1);
        questionDAO.addQuestion(question, quizId);

        Answer answer = new Answer(answerInput, question.getId(), true);
        questionDAO.addAnswer(answer, question.getId());

        req.getSession().setAttribute("questionCount", (int) req.getSession().getAttribute("questionCount") + 1);
        System.out.println("QUESTION COUNT:" + req.getSession().getAttribute("questionCount"));

        if ((int) req.getSession().getAttribute("questionCount") == Integer.parseInt((String) req.getSession().getAttribute("numberOfQuestions"))) {
            req.getRequestDispatcher("QuizCreated.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("Questions.jsp").forward(req, resp);
        }
        return;

    }
}
