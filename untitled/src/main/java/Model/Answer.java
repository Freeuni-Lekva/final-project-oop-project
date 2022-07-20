package Model;

public class Answer {

    private long id;
    private String text;
    private long questionId;
    private boolean isCorrect;

    public Answer(long id, String text, long questionId, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }

    public Answer(String text, long questionId, boolean isCorrect) {
        this.text = text;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean aTrue) {
        isCorrect = aTrue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}