package com.quizzetta.DAOs;

import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestionDAO implements QuestionDAO, AnswerDAO {

    private final Connection myConn;

    public MultipleChoiceQuestionDAO (Connection conn) {
        myConn = conn;
    }

    @Override
    public void addAnswer(ArrayList<Answer> answers, long questionId) {
        try {
            for (Answer ans : answers) {
                PreparedStatement stm = myConn.prepareStatement("INSERT INTO multipleChoiceAnswers (answer_text," +
                                " question_id, is_correct) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stm.setString(1, ans.getText());
                stm.setLong(2, questionId);
                stm.setBoolean(3, ans.isCorrect());
                stm.execute();
                ResultSet res = stm.getGeneratedKeys();
                res.next();
                long answerId = res.getLong(1);
                ans.setId(answerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Answer> getAnswer(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM multipleChoiceAnswers WHERE question_id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        List<Answer> lst = new ArrayList<>();
        while (res.next()) {
            lst.add(new Answer(res.getLong("id"), res.getString("answer_text"),
                    res.getLong("question_id"), res.getBoolean("is_correct")));
        }
        return lst;
    }

    @Override
    public void removeAnswer(long answerId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM multipleChoiceAnswers WHERE id = ?");
        stm.setLong(1, answerId);
        stm.executeUpdate();
    }

    @Override
    public void addQuestion(Question question, long quizId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO multipleChoiceQuestions (question_text, " +
                            "quiz_id, num_of_answers) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, question.getText());
            stm.setLong(2, quizId);
            stm.setInt(3, question.getNumOfAnswers());
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long questionId = res.getLong(1);
            question.setId(questionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question getQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM multipleChoiceQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new Question (res.getLong("id"), res.getString("question_text"),
                res.getLong("quiz_id"), "", res.getInt("num_of_answers"));

    }

    @Override
    public List<Question> getAllQuestions(long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM multipleChoiceQuestions WHERE quiz_id = ?");
        stm.setLong(1, quizId);
        List<Question> questionList = new ArrayList<>();
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            questionList.add(getQuestion(res.getLong("id")));
        }
        return questionList;
    }

    @Override
    public void removeQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM multipleChoiceQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        stm.executeUpdate();
    }
}