package Quiz.SessionManagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LogIn extends HttpServlet {
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
        super.doPost(req, resp);
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("currentUserID"); // TODO
        String userName = (String) request.getSession().getAttribute("currentUserName"); // TODO

        return userId != null && userName != null;
    }
}
