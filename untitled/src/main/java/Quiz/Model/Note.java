package Quiz.Model;

import java.sql.Timestamp;

public class Note {

    private long id;
    private String noteText;
    private long fromUserId;
    private long toUserId;
    private Timestamp sentTime;

    public Note (long id, String noteText, long fromUserId, long toUserId, Timestamp sentTime) {
        this.id = id;
        this.noteText = noteText;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.sentTime = sentTime;
    }

    public Note(String noteText, long fromUserId, long toUserId, Timestamp sentTime) {
        this.noteText = noteText;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.sentTime = sentTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public Timestamp getSentTime() {
        return  sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }
}
