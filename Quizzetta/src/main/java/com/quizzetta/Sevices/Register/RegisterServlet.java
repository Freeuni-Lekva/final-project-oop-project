package com.quizzetta.Sevices.Register;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Hasher;
import com.quizzetta.Model.User;
import com.quizzetta.Validator.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RegisterServlet")
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
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("hehehe");
        System.out.println(String.format("%s %s %s %s", username, password, firstName, lastName));

//        String email = req.getParameter("email");

        boolean isAdmin = false;
        System.out.println("CONNECTIONAMDE AR MISULI: ");

        Connection dataBaseConn = (Connection) req.getServletContext().getAttribute("dataBaseConn"); // TODO needs review

        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("UserDAO");
        System.out.println("ADD USERAMDE AR MISULI: ");

//        userDAO.addUser(new User("vmama20", Hasher.generateHash("pass"), "Vazha",
//                "Mamatsashvili", false));
        System.out.println("ADD USER MOXDA: ");


        User test1 = userDAO.getUser("vmama20");
        System.out.println(test1.getUsername());

        System.out.println("ERRORAMDE AR MISULI: ");

        // TODO REGISTER NEEDS TO BE VALIDATED

        // ERRORS
        List<AppError> errors = new ArrayList<>();
        RegisterValidator regVal = new RegisterValidator(username, firstName, lastName, password, userDAO);
        System.out.println("SQLAMDE AR MISULI: ");

        if (!regVal.validate()) {
            System.out.println("Not Validated: ");
            errors = regVal.getErrors();
            for (AppError e : errors) {
                System.out.println(e);
            }
        }


        // check for caught errors
//        if (errors.size() != 0) {
//            System.out.println("GSONSHI SHEMOSVLAAA");
//            Gson gson = new Gson();
//            resp.getWriter().println(gson.toJson(errors));
//            return;
//        }

        for (AppError s : errors) {
            System.out.println(s.getErrorMessage());
        }

        // Add User to the Database.
        String passwordHash = Hasher.generateHash(password); // TODO LOGIC BEHIND THIS NEEDS TO BE REVIEWED
        User user = new User(username, passwordHash, firstName, lastName, isAdmin);

        userDAO.addUser(user);
        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());

        req.getRequestDispatcher("HomepageLoggedIn.jsp").forward(req, resp);

    }


    // Can change to only check if userId is not null.
    private boolean isLoggedIn(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId"); // TODO  MAKE SURE THE KEYWORD WORKS
        String userName = (String) request.getSession().getAttribute("username"); // TODO SAME AS ABOVE

        return userId != null && userName != null;
    }


}
