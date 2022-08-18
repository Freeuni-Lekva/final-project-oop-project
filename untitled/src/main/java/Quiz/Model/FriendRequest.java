package Quiz.Model;

public class FriendRequest {

    private long id;
    private long fromId;
    private long toId;

    public FriendRequest(long id, long fromId, long toId) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
    }

    public FriendRequest(long fromId, long toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }
}
