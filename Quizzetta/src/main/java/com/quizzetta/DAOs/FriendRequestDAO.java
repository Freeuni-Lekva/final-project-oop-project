package com.quizzetta.DAOs;

import com.quizzetta.Model.FriendRequest;

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

    public void addFriendRequest(FriendRequest request) {
        PreparedStatement stm = null;

        try {
            stm = myConn.prepareStatement("INSERT INTO friendRequests (from_id, to_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setLong(1, request.getFromId());
            stm.setLong(2, request.getToId());
            stm.executeUpdate();
            ResultSet res = stm.getGeneratedKeys();
            res.next();
            long requestId = res.getLong(1);
            request.setId(requestId);
            stm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FriendRequest getFriendRequest(long requestId) {
        PreparedStatement stm;

        try {
            stm = myConn.prepareStatement("SELECT * FROM friendRequests WHERE id = ?");
            stm.setLong(1, requestId);
            ResultSet res = stm.executeQuery();
            res.next();
            stm.close();
            return new FriendRequest(res.getLong("id"), res.getLong("from_id"),
                    res.getLong("to_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FriendRequest> getAllRequests(long userId) {
        PreparedStatement stm;

        try {
            stm = myConn.prepareStatement("SELECT  * FROM friendRequests WHERE to_id = ?");
            stm.setLong(1, userId);
            List<FriendRequest> allRequests = new ArrayList<>();
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                allRequests.add(getFriendRequest(res.getLong("id")));
            }
            stm.close();
            return allRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFriendRequest(long requestId) throws SQLException {
        PreparedStatement stm;

        try {
            stm = myConn.prepareStatement("DELETE FROM friendRequests WHERE id = ?");
            stm.setLong(1, requestId);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}