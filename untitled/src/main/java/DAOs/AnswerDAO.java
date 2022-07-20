package DAOs;

import Model.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AnswerDAO {
    void addAnswer (ArrayList<Answer> answers, long questionId) throws SQLException;
    List<Answer> getAnswer (long questionId) throws SQLException;
    void removeAnswer (long answerId) throws SQLException;
}