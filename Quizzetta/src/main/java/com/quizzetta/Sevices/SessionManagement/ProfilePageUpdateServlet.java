package com.quizzetta.Sevices.SessionManagement;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProfilePageUpdateServlet")
public class ProfilePageUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("userId");

//        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String url = req.getParameter("imageUrl");

        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("UserDAO");
        User user = null;
        try {
            user = userDAO.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

    }
}
