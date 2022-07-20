package DAOs;

import Model.FriendRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {

    private final Connection myConn;

    public FriendRequestDAO(Connection myConn) {
        this.myConn = myConn;
    }

    public void addFriendRequest (FriendRequest request) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("INSERT INTO friendRequests (from_id, to_id) VALUES (?, ?)",
                                                        PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setLong(1, request.getFromId());
        stm.setLong(2, request.getToId());
        stm.executeUpdate();
        ResultSet res = stm.getGeneratedKeys();
        res.next();
        long requestId = res.getLong(1);
        request.setId(requestId);
    }

    public FriendRequest getFriendRequest (long requestId) throws  SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT * FROM friendRequests WHERE id = ?");
        stm.setLong(1, requestId);
        ResultSet res = stm.executeQuery();
        res.next();
        return new FriendRequest(res.getLong("id"), res.getLong("from_id"),
                                 res.getLong("to_id"));
    }

    public List<FriendRequest> getAllRequests (long userId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("SELECT  * FROM friendRequests WHERE to_id = ?");
        stm.setLong(1, userId);
        List<FriendRequest> allRequests = new ArrayList<>();
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            allRequests.add(getFriendRequest(res.getLong("id")));
        }
        return allRequests;
    }

    public void removeFriendRequest (long requestId) throws SQLException {
        PreparedStatement stm = myConn.prepareStatement("DELETE FROM friendRequests WHERE id = ?");
        stm.setLong(1, requestId);
        stm.executeUpdate();
    }
}
