package com.quizzetta.DAOs;

import com.quizzetta.Model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    void addQuestion (Question question, long quizId) throws SQLException;
    Question getQuestion (long questionId) throws SQLException;
    List<Question> getAllQuestions (long quizId) throws SQLException;
    void removeQuestion (long questionId) throws SQLException;
}