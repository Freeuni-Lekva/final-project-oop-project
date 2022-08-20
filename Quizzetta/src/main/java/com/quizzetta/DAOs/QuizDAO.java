package com.quizzetta.DAOs;

import com.quizzetta.Model.Question;
import com.quizzetta.Model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    private final Connection myConn;

    public QuizDAO(Connection myConn) {
        this.myConn = myConn;
    }

    public void addQuiz (Quiz quiz) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO quizzes (title, creator_id, " +
                                                        "are_random_questions, is_one_page, is_immediate_feedback, " +
                                                        "is_practice_mode) VALUES (?, ?, ?, ?, ?, ?)",
                                                        PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, quiz.getTitle());
        stm.setLong(2, quiz.getCreatorUserId());
        stm.setBoolean(3, quiz.isRandomQuestions());
        stm.setBoolean(4, quiz.isOnePage());
        stm.setBoolean(5, quiz.isImmediateFeedback());
        stm.setBoolean(6, quiz.isPracticeMode());
        stm.executeUpdate();
        ResultSet res = stm.getGeneratedKeys();
        res.next();
        long quizId = res.getLong(1);
        quiz.setId(quizId);
    }

    public Quiz getQuiz (long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE id = ?");
        stm.setLong(1, quizId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new Quiz (res.getLong("id"), res.getString("title"),
                         res.getLong("creator_id"), res.getBoolean("are_random_questions"),
                         res.getBoolean("is_one_page"), res.getBoolean("is_immediate_feedback"),
                         res.getBoolean("is_practice_mode"));
    }

    public void removeQuiz (long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM quizzes WHERE id = ?");
        stm.setLong(1, quizId);
        stm.executeUpdate();
    }

    public List<Question> getQuizQuestions (long quizId) throws SQLException {
        StandardTextQuestionDAO standard = new StandardTextQuestionDAO(myConn);
        List<Question> res = new ArrayList<>(standard.getAllQuestions(quizId));
        FillTheBlankQuestionDAO fillBlank = new FillTheBlankQuestionDAO(myConn);
        res.addAll(fillBlank.getAllQuestions(quizId));
        MultipleChoiceQuestionDAO multiple = new MultipleChoiceQuestionDAO(myConn);
        res.addAll(multiple.getAllQuestions(quizId));
        PictureResponseQuestionDAO picture = new PictureResponseQuestionDAO(myConn);
        res.addAll(picture.getAllQuestions(quizId));
        return res;
    }

    public List<Quiz> getCreatedQuizzes (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ?");
        stm.setLong(1, userId);
        ResultSet res = stm.executeQuery();
        List<Quiz> createdQuizzes = new ArrayList<>();
        while (res.next()) {
            createdQuizzes.add(getQuiz(res.getLong("id")));
        }
        return createdQuizzes;
    }

    public List<Quiz> getAllQuizzes() throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT  * FROM  quizzes ORDER BY id ASC");
        ResultSet res = stm.executeQuery();
        List<Quiz> allQuizzes = new ArrayList<>();
        while (res.next()) {
            allQuizzes.add(getQuiz(res.getLong("id")));
        }
        return allQuizzes;
    }
}
