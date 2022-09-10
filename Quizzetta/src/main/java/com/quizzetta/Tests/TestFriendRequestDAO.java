import com.quizzetta.DAOs.FriendRequestDAO;
import com.quizzetta.Model.DatabaseConnector;
import com.quizzetta.Model.FriendRequest;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestFriendRequestDAO extends TestCase {

    private FriendRequestDAO friendRequestDao;
    private FriendRequest req1;
    private FriendRequest req2;
    private FriendRequest req3;

    public void setUp() throws SQLException, ClassNotFoundException {
        Connection myConn = DatabaseConnector.getConnection();
        friendRequestDao = new FriendRequestDAO(myConn);
        req1 = new FriendRequest(1, 2);
        req2 = new FriendRequest(1, 3);
        req3 = new FriendRequest(3, 2);
        friendRequestDao.addFriendRequest(req1);
        friendRequestDao.addFriendRequest(req2);
        friendRequestDao.addFriendRequest(req3);
    }

    public void testFriendRequest() throws SQLException {
        FriendRequest temp1 = friendRequestDao.getFriendRequest(req1.getId());
        assertEquals(req1.getFromId(), temp1.getFromId());
        assertEquals(req1.getToId(), temp1.getToId());
        assertEquals(req1.getId(), temp1.getId());
        FriendRequest temp2 = friendRequestDao.getFriendRequest(req2.getId());
        assertEquals(req2.getFromId(), temp2.getFromId());
        assertEquals(req2.getToId(), temp2.getToId());
        assertEquals(req2.getId(), temp2.getId());
        FriendRequest temp3 = friendRequestDao.getFriendRequest(req3.getId());
        assertEquals(req3.getFromId(), temp3.getFromId());
        assertEquals(req3.getToId(), temp3.getToId());
        assertEquals(req3.getId(), temp3.getId());
    }

    public void testAllFriendRequests() throws SQLException {
        List<FriendRequest> allRequests = friendRequestDao.getAllRequests(2);
        assertEquals(1, allRequests.get(0).getId());
        assertEquals(3, allRequests.get(1).getId());
    }
}
