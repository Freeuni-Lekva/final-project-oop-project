package Quiz.Validator;

import Quiz.Errors.AppError;

import java.sql.SQLException;
import java.util.List;

public class UserNameValidator implements Validator {

    private final String userName;

    public UserNameValidator(String userName) {
        this.userName = userName;
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
