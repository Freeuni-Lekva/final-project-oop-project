package com.quizzetta.DAOs;

import com.quizzetta.Model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    void addQuestion (Question question, long quizId);
    Question getQuestion (long questionId);
    List<Question> getAllQuestions (long quizId);
    void removeQuestion (long questionId);
}