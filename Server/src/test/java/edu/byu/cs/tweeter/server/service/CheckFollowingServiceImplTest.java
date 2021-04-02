package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckFollowingServiceImplTest {

    @Test
    void checkIfFollowingTest() throws IOException {
        User user1 = new User("firstname", "lastname", "alias", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");
        CheckIfFollowingRequest request = new CheckIfFollowingRequest(user1, user2, "");
        CheckFollowingServiceImpl service = new CheckFollowingServiceImpl();
        CheckIfFollowingResponse response = service.checkIfFollowing(request);
        assertFalse(response.isFollowingUser());
    }

}
