package DAOs;

import Model.Answer;
import Model.Question;
import Model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandardTextQuestionDAO implements QuestionDAO, AnswerDAO {

    private final Connection myConn;

    public StandardTextQuestionDAO(Connection myConn) {
        this.myConn = myConn;
    }

    @Override
    public void addAnswer(ArrayList<Answer> answers, long questionId) throws SQLException {
        for (Answer ans : answers) {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO standardTextAnswers (answer_text, question_id) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, ans.getText());
            stm.setLong(2, questionId);
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long answerId = res.getLong(1);
            ans.setId(answerId);
            ans.setIsCorrect(true);
        }
    }

    @Override
    public List<Answer> getAnswer(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM standardTextAnswers WHERE question_id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        List<Answer> lst = new ArrayList<>();
        while (res.next()) {
            lst.add(new Answer(res.getLong("id"), res.getString("answer_text"), res.getLong("question_id"), true));
        }
        return lst;
    }

    @Override
    public void addQuestion(Question question, long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO standardTextQuestions (question_text, quiz_id) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, question.getText());
        stm.setLong(2, quizId);
        stm.execute();
        ResultSet res = stm.getGeneratedKeys();
        res.next();
        long questionId = res.getLong(1);
        question.setId(questionId);
    }

    @Override
    public Question getQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM standardTextQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new Question (res.getLong("id"), res.getString("question_text"), res.getLong("quiz_id"), "", 1);
    }

    @Override
    public List<Question> getAllQuestions(long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM standardTextQuestions WHERE quiz_id = ?");
        stm.setLong(1, quizId);
        List<Question> questionList = new ArrayList<>();
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            questionList.add(getQuestion(res.getLong("id")));
        }
        return questionList;
    }
}
