package DAOs;

import Model.Question;

import java.sql.SQLException;

public interface QuestionDAO {
    void addQuestion (Question question, long quizId) throws SQLException;
    void addImageQuestion (String url, Question question, long quizId);
    Question getQuestion (long questionId) throws SQLException;
    Question getUrl (long questionId);
}
