package Model;

import java.sql.Timestamp;

public class TakenQuiz {

    private long userId;
    private long quizId;
    private double userScore;
    private Timestamp startTime;
    private Timestamp endTime;

    public TakenQuiz(long userId, long quizId, double userScore, Timestamp startTime, Timestamp endTime) {
        this.userId = userId;
        this.quizId = quizId;
        this.userScore = userScore;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
