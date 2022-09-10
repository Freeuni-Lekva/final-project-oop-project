package com.quizzetta.Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Quiz {

    private long id;
    private String title;
    private long creatorUserId;
    private boolean randomQuestions;
    private boolean onePage;
    private boolean immediateFeedback;
    private boolean practiceMode;
    private List<Question> questions;
    private int numberOfUses;

    private Timestamp creationDate;

    private int authorId;


    public Quiz(long id, String title, long creatorUserId, boolean randomQuestions, boolean onePage,
                boolean immediateFeedback, boolean practiceMode) {
        this.id = id;
        this.title = title;
        this.creatorUserId = creatorUserId;
        this.randomQuestions = randomQuestions;
        this.onePage = onePage;
        this.immediateFeedback = immediateFeedback;
        this.practiceMode = practiceMode;
        this.numberOfUses = 0;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Quiz (String title, long creatorUserId, boolean randomQuestions, boolean onePage,
                 boolean immediateFeedback, boolean practiceMode) {
        this.title = title;
        this.creatorUserId = creatorUserId;
        this.randomQuestions = randomQuestions;
        this.onePage = onePage;
        this.immediateFeedback = immediateFeedback;
        this.practiceMode = practiceMode;
        this.numberOfUses = 0;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public boolean isRandomQuestions() {
        return randomQuestions;
    }

    public void setRandomQuestions(boolean randomQuestions) {
        this.randomQuestions = randomQuestions;
    }

    public boolean isOnePage() {
        return onePage;
    }

    public void setOnePage(boolean onePage) {
        this.onePage = onePage;
    }

    public boolean isImmediateFeedback() {
        return immediateFeedback;
    }

    public void setImmediateFeedback(boolean immediateFeedback) {
        this.immediateFeedback = immediateFeedback;
    }

    public boolean isPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(boolean practiceMode) {
        this.practiceMode = practiceMode;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void incrementNumberOfUses() { this.numberOfUses++; }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
}