package com.quizzetta.Model;

public class StandardAnswer1 implements AnswerTest {

    private String answer;

    public StandardAnswer1(String answer) {
        this.answer = answer;
    }

    String getAnswer() {
        return this.answer;
    }
}
