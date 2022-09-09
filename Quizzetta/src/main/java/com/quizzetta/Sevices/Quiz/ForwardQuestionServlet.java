package com.quizzetta.Sevices.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ForwardQuestionServlet")
public class ForwardQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("questionType");
        if (type.equals("standard")) {
            req.getRequestDispatcher("ResponseQuestion.jsp").forward(req, resp);
            return;
        } else if (type.equals("blank")) {
            req.getRequestDispatcher("FillBlankQuestion.jsp").forward(req, resp);
            return;

        } else if (type.equals("multiple")) {
            req.getRequestDispatcher("MultipleChoiceQuestion.jsp").forward(req, resp);
            return;

        } else if (type.equals("picture")) {
            req.getRequestDispatcher("PictureResponseQuestion.jsp").forward(req, resp);
            return;
        }

        System.out.println();

    }
}
