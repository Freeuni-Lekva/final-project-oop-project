package com.quizzetta.Sevices.SessionManagement;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Model.User;
import com.quizzetta.Validator.LoginValidator;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
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
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginValidator loginValidator = new LoginValidator(username, password, userDAO);

        if (!loginValidator.validate()) {
            List<AppError> errors = loginValidator.getErrors();
            Gson gson = new Gson();
            resp.getWriter().print(gson.toJson(errors));
            // TODO SEND ERROR TO THE JSP
            return;
        }

        User user = userDAO.getUser(username);

        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());
        req.getRequestDispatcher("HomepageLoggedIn.jsp").forward(req, resp);


//        try { // User exists in database
//            User user = userDAO.getUser(username);
//            req.getSession().setAttribute("currentUserId", user.getId()); // TODO
//            req.getSession().setAttribute("currentUserName", user.getUsername()); // TODO
//        } catch (SQLException e) { // User doesn't exist in database
//            e.printStackTrace();
//        }


    }

    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId"); // TODO
        String userName = (String) request.getSession().getAttribute("username"); // TODO

        return userId != null && userName != null;
    }
}
