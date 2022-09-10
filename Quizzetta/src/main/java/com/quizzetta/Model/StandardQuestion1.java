package com.quizzetta.Model;

public class StandardQuestion1 implements QuestionTest {

    private final String text;
    private final StandardAnswer1 answer;
    private long id;
    private final long quizId;


    public StandardQuestion1(long id, String text, StandardAnswer1 answer, long quizId) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.quizId = quizId;
    }


    public StandardQuestion1(String text, StandardAnswer1 answer, long quizId) {
        this.text = text;
        this.answer = answer;
        this.quizId = quizId;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public long getQuizId() {
        return quizId;
    }

    @Override
    public void setAnswer() {
    }

    @Override
    public AnswerTest getAnswer() {
        return answer;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getNumOfAnswers() {
        return 1;
    }
}
