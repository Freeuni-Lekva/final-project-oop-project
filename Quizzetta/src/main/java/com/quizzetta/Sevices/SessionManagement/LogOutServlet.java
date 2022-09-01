package com.quizzetta.Sevices.SessionManagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeCurrentSession(req);
        resp.sendRedirect("Homepage.jsp");
//        req.getRequestDispatcher("Homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void removeCurrentSession(HttpServletRequest request) {
        request.getSession().setAttribute("userId", null); // TODO
        request.getSession().setAttribute("username", null); // TODO
    }
}
