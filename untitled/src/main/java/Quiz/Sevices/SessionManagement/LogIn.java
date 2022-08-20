package Quiz.Sevices.SessionManagement;

import Quiz.DAOs.UserDAO;
import Quiz.Errors.AppError;
import Quiz.Model.User;
import Quiz.Validator.LogInValidator;
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
        Connection dataBaseConn = (Connection)req.getServletContext().getAttribute("dataBaseConn");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LogInValidator logInValidator = new LogInValidator(username, password, dataBaseConn);

        try {
            if (logInValidator.validate()) {
                List<AppError> errors = logInValidator.getErrors();

                Gson gson = new Gson();
                resp.getWriter().print(gson.toJson(errors));

                return;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        UserDAO userDAO = (UserDAO) req.getSession().getAttribute("UserDAO");

        try {
            User user = userDAO.getUser(username);

            req.getSession().setAttribute("currenUserId", user.getId()); // TODO
            req.getSession().setAttribute("currentUserName", user.getUsername()); // TODO
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("currentUserID"); // TODO
        String userName = (String) request.getSession().getAttribute("currentUserName"); // TODO

        return userId != null && userName != null;
    }
}
