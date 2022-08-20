package Quiz.Validator;

import Quiz.Errors.AppError;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LogInValidator implements Validator {

    private String userName;
    private String password;

    public LogInValidator(String userName, String password, Connection connection) {
        this.userName = userName;
        this.password = password;

    }
    @Override
    public boolean validate() throws SQLException {
        return false;
    }

    @Override
    public List<AppError> getErrors() {
        return null;
    }
}
