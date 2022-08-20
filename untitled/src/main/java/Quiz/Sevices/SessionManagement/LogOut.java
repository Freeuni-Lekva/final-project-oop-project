package Quiz.Sevices.SessionManagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/logout")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeCurrentSession(req);
        resp.sendRedirect("/quiz/login"); // TODO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeCurrentSession(req);
        resp.sendRedirect("/quiz/login"); // TODO
    }

    private void removeCurrentSession(HttpServletRequest request) {
        request.getSession().setAttribute("currentUserId", null); // TODO
        request.getSession().setAttribute("currentUserName", null); // TODO
    }
}
