package com.quizzetta.DAOs;

import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;
import com.quizzetta.Model.QuestionTest;
import com.quizzetta.Model.StandardQuestion1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandardTextQuestionDAO1 {

    private final Connection myConn;

    public StandardTextQuestionDAO1(Connection myConn) {
        this.myConn = myConn;
    }

    public void addQuestion(StandardQuestion1 question) {
        try {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO standardTextQuestions " +
                            "(question_text, answer_text, quiz_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, question.getText());
            stm.setString(2, question.getText());
            stm.setObject(3, question.getQuizId());
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            System.out.println(res.getLong(1));
            question.setId(res.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Question getQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM standardTextQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new Question(res.getLong("id"), res.getString("question_text"),
                res.getLong("quiz_id"), "", 1);
    }

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