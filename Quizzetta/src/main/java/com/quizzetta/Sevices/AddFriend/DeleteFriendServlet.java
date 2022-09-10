package com.quizzetta.Sevices.AddFriend;

import com.quizzetta.DAOs.FriendRequestDAO;
import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("User") == null) {
            resp.sendRedirect("/homepage");
            return;
        }

        User currentUser = (User) req.getSession().getAttribute("User");
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("UserDAO");

        User friend = null;
        friend = userDAO.getUser(req.getParameter("Username"));

        FriendRequestDAO friendRequestDAO = (FriendRequestDAO) req.getServletContext().getAttribute("FriendRequestDAO");

        userDAO.removeFriend(currentUser.getId(), friend.getId());

        resp.sendRedirect("/profile?Username=" + friend.getUsername()); // TODO to active user profile or main page
    }
}
