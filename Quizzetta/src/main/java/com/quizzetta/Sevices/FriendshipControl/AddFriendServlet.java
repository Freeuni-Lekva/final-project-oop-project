package com.quizzetta.Sevices.FriendshipControl;


import com.quizzetta.DAOs.FriendRequestDAO;
import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.Model.FriendRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/SendFriendRequest")
public class AddFriendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sendID = Integer.parseInt(req.getParameter("sender"));
        int receiverID = Integer.parseInt(req.getParameter("receiver"));

        FriendRequestDAO friendRequestDAO = (FriendRequestDAO) req.getServletContext().getAttribute("FriendRequestDAO");
        FriendRequest friendRequest = new FriendRequest(sendID, receiverID);

        friendRequestDAO.addFriendRequest(friendRequest);

        resp.sendRedirect(""); // TODO TO USER
    }
}