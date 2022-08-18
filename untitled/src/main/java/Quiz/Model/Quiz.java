package Quiz.Model;

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


    public Quiz(long id, String title, long creatorUserId, boolean randomQuestions, boolean onePage,
                boolean immediateFeedback, boolean practiceMode) {
        this.id = id;
        this.title = title;
        this.creatorUserId = creatorUserId;
        this.randomQuestions = randomQuestions;
        this.onePage = onePage;
        this.immediateFeedback = immediateFeedback;
        this.practiceMode = practiceMode;
    }

    public Quiz(String title, long creatorUserId, boolean randomQuestions, boolean onePage, boolean immediateFeedback, boolean practiceMode) {
        this.title = title;
        this.creatorUserId = creatorUserId;
        this.randomQuestions = randomQuestions;
        this.onePage = onePage;
        this.immediateFeedback = immediateFeedback;
        this.practiceMode = practiceMode;
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
}
