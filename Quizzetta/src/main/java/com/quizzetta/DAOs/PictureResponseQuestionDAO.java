package com.quizzetta.DAOs;

import com.quizzetta.Model.Answer;
import com.quizzetta.Model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureResponseQuestionDAO implements QuestionDAO, AnswerDAO {

    private final Connection myConn;

    public PictureResponseQuestionDAO(Connection conn) {
        myConn = conn;
    }

    @Override
    public void addAnswer(ArrayList<Answer> answers, long questionId) throws SQLException {
        for (Answer ans : answers) {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO pictureResponseAnswers " +
                            "(answer_text, question_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, ans.getText());
            stm.setLong(2, questionId);
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long answerId = res.getLong(1);
            ans.setId(answerId);
            ans.setIsCorrect(true);
            stm.close();
        }
    }

    public void addAnswer(Answer answer, long questionId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO pictureResponseAnswers " +
                            "(answer_text, question_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, answer.getText());
            stm.setLong(2, questionId);
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long answerId = res.getLong(1);
            answer.setId(answerId);
            answer.setIsCorrect(true);
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Answer> getAnswer(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM pictureResponseAnswers WHERE question_id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        List<Answer> lst = new ArrayList<>();
        while (res.next()) {
            lst.add(new Answer(res.getLong("id"), res.getString("answer_text"),
                    res.getLong("question_id"), true));
        }
        stm.close();
        return lst;
    }

    @Override
    public void removeAnswer(long answerId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM pictureResponseAnswers WHERE id = ?");
        stm.setLong(1, answerId);
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public void addQuestion(Question question, long quizId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO pictureResponseQuestions " +
                            "(question_text, url, quiz_id) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, question.getText());
            stm.setString(2, question.getImageUrl());
            stm.setLong(3, quizId);
            stm.execute();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long questionId = res.getLong(1);
            question.setId(questionId);
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question getQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM pictureResponseQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        ResultSet res = stm.executeQuery();
        res.next();
        stm.close();
        return new Question(res.getLong("id"), res.getString("question_text"),
                res.getLong("quiz_id"), res.getString("url"), 1);
    }

    @Override
    public List<Question> getAllQuestions(long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM pictureResponseQuestions WHERE quiz_id = ?");
        stm.setLong(1, quizId);
        List<Question> questionList = new ArrayList<>();
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            questionList.add(getQuestion(res.getLong("id")));
        }
        stm.close();
        return questionList;
    }

    @Override
    public void removeQuestion(long questionId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM pictureResponseQuestions WHERE id = ?");
        stm.setLong(1, questionId);
        stm.executeUpdate();
        stm.close();
    }
}