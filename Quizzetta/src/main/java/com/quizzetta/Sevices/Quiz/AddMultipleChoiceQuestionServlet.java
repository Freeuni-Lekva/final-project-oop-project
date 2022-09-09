package com.quizzetta.Sevices.Quiz;

import com.quizzetta.DAOs.MultipleChoiceQuestionDAO;
import com.quizzetta.DAOs.StandardTextQuestionDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;
import com.quizzetta.Model.Quiz;
import com.quizzetta.Validator.MultipleChoiceQuestionValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MultipleChoiceQuestion")
public class AddMultipleChoiceQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionInput = req.getParameter("question");

        ArrayList<Answer> answers = new ArrayList<>();

        String answer1 = req.getParameter("answer1");
        String answer2 = req.getParameter("answer2");
        String answer3 = req.getParameter("answer3");
        String correctAnswer = req.getParameter("correctAnswer");

        Quiz quiz = (Quiz) req.getSession().getAttribute("quiz");
        long quizId = quiz.getId();

        MultipleChoiceQuestionDAO questionDAO = (MultipleChoiceQuestionDAO) req.getServletContext().getAttribute("MultipleChoiceQuestionDAO");

        Question question = new Question(questionInput, quizId, null, 1);

        answers.add(new Answer(answer1, question.getId(), false));
        answers.add(new Answer(answer2, question.getId(), false));
        answers.add(new Answer(answer3, question.getId(), false));
        answers.add(new Answer(correctAnswer, question.getId(), true));

        System.out.println(" multiple validatoramde");


        MultipleChoiceQuestionValidator validator = new MultipleChoiceQuestionValidator(questionInput, answers);

        if (!validator.validate()) {
            System.out.println("shemosvla multiple validatorshi");

            List<ValidationError> errors = validator.getErrors();
            for (ValidationError v : errors) {
                System.out.println(v.getErrorMessage());
            }

            // dispatch...
            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("MultipleChoiceQuestion.jsp").forward(req, resp);
            return;
        }

        questionDAO.addQuestion(question, quizId);
        questionDAO.addAnswer(answers, question.getId());

        System.out.println("Checkmark 1");
        System.out.println(req.getSession().getAttribute("questionCount"));


        req.getSession().setAttribute("questionCount", (int) req.getSession().getAttribute("questionCount") + 1);
        System.out.println("QUESTION COUNT:" + req.getSession().getAttribute("questionCount"));

        System.out.println("Checkmark 2");


        if ((int) req.getSession().getAttribute("questionCount") == Integer.parseInt((String) req.getSession().getAttribute("numberOfQuestions"))) {
            System.out.println("AQ RATOM AR VART?");
            req.getRequestDispatcher("QuizCreated.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("Questions.jsp").forward(req, resp);
        }
        return;

    }
}
