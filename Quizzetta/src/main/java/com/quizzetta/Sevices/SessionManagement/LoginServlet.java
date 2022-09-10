package com.quizzetta.Sevices.SessionManagement;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.Model.User;
import com.quizzetta.Validator.LogInValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isLoggedIn(req)) {
            resp.sendRedirect("/quiz/"); // TODO
            return;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(""); // TODO LOGIN JS
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("UserDAO");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LogInValidator loginValidator = new LogInValidator(username, password, userDAO);

        if (!loginValidator.validate()) {
            List<ValidationError> errors = loginValidator.getErrors();
            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
            return;
        }

        User user = null;
        try {
            user = userDAO.getUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("AQAMDE MOVAGWIET 1");

        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());

        System.out.println("AQAMDE MOVAGWIET 2");


        // Adding these for Profile page, could move these to a different servlet instead.
        req.getSession().setAttribute("userEmail", user.getEmail());
        req.getSession().setAttribute("userFirstName", user.getFirstName());
        req.getSession().setAttribute("userLastName", user.getLastName());
        req.getSession().setAttribute("userFriends", user.getFriends());

        System.out.println("AQAMDE MOVAGWIET 3");



        resp.sendRedirect("HomepageLoggedIn.jsp");
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("username");

        return userId != null && userName != null;
    }
}
