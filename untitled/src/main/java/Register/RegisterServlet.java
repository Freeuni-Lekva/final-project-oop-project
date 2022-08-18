package Register;

import DAOs.UserDAO;
import Errors.AppError;
import Model.Hasher;
import Model.User;
import Validator.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class RegisterServlet extends HttpServlet {
    private static final String REGISTER_PAGE_PATH = ""; // TODO

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isLoggedIn(req)) {
            resp.sendRedirect("/quiz/"); // TODO MAYBE NEED TO CHANGE THE NAME
            return;
        }

        req.getRequestDispatcher(REGISTER_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String firstName = req.getParameter("username");
        String lastName = req.getParameter("username");

        boolean isAdmin = false;

        Connection dataBaseConn = (Connection) req.getServletContext().getAttribute("dataBaseConn"); // TODO needs review

        // TODO REGISTER NEEDS TO BE VALIDATED

        // ERRORS
        List<AppError> errors = new ArrayList<>();
        RegisterValidator regVal = new RegisterValidator(username, firstName, lastName, password, dataBaseConn);

        try {
            if (regVal.validate()) {
                errors = regVal.getErrors();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        // check for caught errors
        if (errors.size() != 0) {
            Gson gson = new Gson();
            resp.getWriter().println(gson.toJson(errors));
            return;
        }

        UserDAO userDAO = (UserDAO) req.getSession().getAttribute("UserDAO");

        String passwordHash = Hasher.generateHash(password); // TODO LOGIC BEHIND THIS NEEDS TO BE REVIEWED
        User user = new User(username, passwordHash, firstName, lastName, isAdmin);

        try {
            userDAO.addUser(user);

            req.getSession().setAttribute("currentUserId", user.getId()); // TODO KEYWORDS NEED TO BE REVIEWED
            req .getSession().setAttribute("currentUserName", user.getUsername()); // TODO KEYWORDS NEED TO BE REVIEWED
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("currentUserId"); // TODO  MAKE SURE THE KEYWORD WORKS
        String userName = (String) request.getSession().getAttribute("currentUserName"); // TODO SAME AS ABOVE

        return userId != null && userName != null;
    }


}
