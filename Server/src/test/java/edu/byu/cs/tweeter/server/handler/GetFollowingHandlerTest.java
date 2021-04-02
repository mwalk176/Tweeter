package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetFollowingHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "alias", "url");
        FollowingRequest request = new FollowingRequest(user1, 10, null, "");
        GetFollowingHandler handler = new GetFollowingHandler();
        FollowingResponse response = handler.handleRequest(request, null);
        System.out.println(response);
        assertNotNull(response);
    }

}
