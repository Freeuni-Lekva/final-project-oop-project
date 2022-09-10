package com.quizzetta.Model;

import java.util.List;

public interface QuestionTest {

//    String getText();
//    String generateQuestionHTML();
    void setAnswer();
    AnswerTest getAnswer();
    public long getId();
    public long getQuizId();
    int getNumOfAnswers();

}
