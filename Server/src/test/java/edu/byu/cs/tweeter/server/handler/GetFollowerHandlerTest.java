package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GetFollowerHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("Test", "User", "testUser",
                "https://mwalktest.s3.us-east-2.amazonaws.com/donald_duck.png");
        FollowerRequest request = new FollowerRequest(user1, 10, null,
                "81246b76-c2ec-4160-b946-b17af08625e5");
        GetFollowerHandler handler = new GetFollowerHandler();
        FollowerResponse response = handler.handleRequest(request, null);
        System.out.println(response);
        request.setLastFollower(response.getFollowers().get(9));
        response = handler.handleRequest(request, null);
        System.out.println(response);
        assertNotNull(response.getFollowers());
        assertTrue(response.isMorePages());
    }
}
