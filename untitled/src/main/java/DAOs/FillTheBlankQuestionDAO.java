package DAOs;

import Model.Answer;
import Model.Question;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class FillTheBlankQuestionDAO implements QuestionDAO, AnswerDAO {

    private final Connection myConn;

    public FillTheBlankQuestionDAO(Connection myConn) {
        this.myConn = myConn;
    }

    @Override
    public void addAnswer(ArrayList<Answer> answers, long questionId) throws SQLException {

    }

    @Override
    public List<Answer> getAnswer(long questionId) throws SQLException {
        return null;
    }

    @Override
    public void addQuestion(Question question, long quizId) throws SQLException {

    }

    @Override
    public Question getQuestion(long questionId) throws SQLException {
        return null;
    }
}
