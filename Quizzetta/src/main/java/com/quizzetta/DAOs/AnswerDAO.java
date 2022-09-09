package com.quizzetta.DAOs;

import com.quizzetta.Model.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AnswerDAO {
    void addAnswer (ArrayList<Answer> answers, long questionId);
    List<Answer> getAnswer (long questionId);
    void removeAnswer (long answerId);
}