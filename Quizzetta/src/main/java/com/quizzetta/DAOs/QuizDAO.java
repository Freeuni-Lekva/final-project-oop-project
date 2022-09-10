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

    public void addQuiz (Quiz quiz) {
        try {
            PreparedStatement stm = myConn.prepareStatement("INSERT INTO quizzes (title, creator_id, " +
                            "creation_time, number_of_takes, " +
                            "are_random_questions, is_one_page, is_immediate_feedback, " +
                            "is_practice_mode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, quiz.getTitle());
            stm.setLong(2, quiz.getCreatorUserId());
            stm.setTimestamp(3, quiz.getCreationDate());
            stm.setInt(4, quiz.getNumberOfUses());
            stm.setBoolean(5, quiz.isRandomQuestions());
            stm.setBoolean(6, quiz.isOnePage());
            stm.setBoolean(7, quiz.isImmediateFeedback());
            stm.setBoolean(8, quiz.isPracticeMode());
            stm.executeUpdate();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long quizId = res.getLong(1);
            quiz.setId(quizId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Quiz getQuiz (long quizId) {
        PreparedStatement stm;
        try {
            stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE id = ?");
            stm.setLong(1, quizId);
            ResultSet res = stm.executeQuery();
            res.next();
            return new Quiz(res.getLong("id"), res.getString("title"),
                    res.getLong("creator_id"), res.getBoolean("are_random_questions"),
                    res.getBoolean("is_one_page"), res.getBoolean("is_immediate_feedback"),
                    res.getBoolean("is_practice_mode"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void removeQuiz (long quizId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM quizzes WHERE id = ?");
        stm.setLong(1, quizId);
        stm.executeUpdate();
    }

    public List<Question> getQuizQuestions (long quizId){
        try {
            StandardTextQuestionDAO standard = new StandardTextQuestionDAO(myConn);
            List<Question> res = new ArrayList<>(standard.getAllQuestions(quizId));
            FillTheBlankQuestionDAO fillBlank = new FillTheBlankQuestionDAO(myConn);
            res.addAll(fillBlank.getAllQuestions(quizId));
            MultipleChoiceQuestionDAO multiple = new MultipleChoiceQuestionDAO(myConn);
            res.addAll(multiple.getAllQuestions(quizId));
            PictureResponseQuestionDAO picture = new PictureResponseQuestionDAO(myConn);
            res.addAll(picture.getAllQuestions(quizId));
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Quiz> getCreatedQuizzes (long userId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ? ORDER BY id ASC");
            stm.setLong(1, userId);
            ResultSet res = stm.executeQuery();
            List<Quiz> createdQuizzes = new ArrayList<>();
            while (res.next()) {
                createdQuizzes.add(getQuiz(res.getLong("id")));
            }
            return createdQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Quiz> getRecentlyCreatedQuizzes (long userId) {
        try {
            PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ? ORDER BY creation_time DESC");
            stm.setLong(1, userId);
            ResultSet res = stm.executeQuery();
            List<Quiz> recentlyCreatedQuizzes = new ArrayList<>();
            while (res.next()) {
                recentlyCreatedQuizzes.add(getQuiz(res.getLong("id")));
            }
            return recentlyCreatedQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Quiz> getRecentlyCreatedQuizzes () {
        try {
            PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes ORDER BY creation_time DESC");
            ResultSet res = stm.executeQuery();
            List<Quiz> recentlyCreatedQuizzes = new ArrayList<>();
            while (res.next()) {
                recentlyCreatedQuizzes.add(getQuiz(res.getLong("id")));
            }
            return recentlyCreatedQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Quiz> getMostPopularQuizzes () {
        try {
            PreparedStatement stm = myConn.prepareStatement("SELECT * FROM quizzes ORDER BY number_of_takes DESC");
            ResultSet res = stm.executeQuery();
            List<Quiz> mostPopularQuizzes = new ArrayList<>();
            while (res.next()) {
                mostPopularQuizzes.add(getQuiz(res.getLong("id")));
            }
            return mostPopularQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Quiz> getAllQuizzes() {
        try {
            PreparedStatement stm = myConn.prepareStatement("SELECT  * FROM  quizzes ORDER BY id ASC");
            ResultSet res = stm.executeQuery();
            List<Quiz> allQuizzes = new ArrayList<>();
            while (res.next()) {
                allQuizzes.add(getQuiz(res.getLong("id")));
            }
            return allQuizzes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
