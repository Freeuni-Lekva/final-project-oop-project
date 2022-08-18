package Quiz.Model;

public class Question {
    private long id;
    private String text;
    private long quizId;
    private String imageUrl;
    private int numOfAnswers;

    public Question(long id, String text, long quizId, String imageUrl, int numOfAnswers) {
        this.id = id;
        this.text = text;
        this.quizId = quizId;
        this.imageUrl = imageUrl;
        this.numOfAnswers = numOfAnswers;
    }

    public Question(String text, long quizId, String imageUrl, int numOfAnswers) {
        this.text = text;
        this.quizId = quizId;
        this.imageUrl = imageUrl;
        this.numOfAnswers = numOfAnswers;
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

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }

    public void setNumOfAnswers(int numOfAnswers) {
        this.numOfAnswers = numOfAnswers;
    }
}
