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
        System.out.println("AQ VART");
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("UserDAO");
        System.out.println(userDAO);
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LogInValidator loginValidator = new LogInValidator(username, password, userDAO);
        System.out.println("AXLA AQQQ :P");

        if (!loginValidator.validate()) {
            System.out.println("SHEMOSVLA");
            List<ValidationError> errors = loginValidator.getErrors();
            System.out.println("Validator passed");
//            Gson gson = new Gson();
//            resp.getWriter().print(gson.toJson(errors));
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

        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());
        resp.sendRedirect("HomepageLoggedIn.jsp");
//        req.getRequestDispatcher("HomepageLoggedIn.jsp").forward(req, resp);


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
