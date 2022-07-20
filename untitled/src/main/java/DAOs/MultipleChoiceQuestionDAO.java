package DAOs;

import Model.Answer;
import Model.Question;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class MultipleChoiceQuestionDAO implements QuestionDAO, AnswerDAO {

    private final Connection myConn;

    public MultipleChoiceQuestionDAO (Connection conn) {
        myConn = conn;
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
