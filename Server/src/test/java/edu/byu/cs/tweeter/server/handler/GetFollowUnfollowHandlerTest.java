package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetFollowUnfollowHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "alias", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");
        FollowUnfollowRequest request = new FollowUnfollowRequest(true, user1, user2, "", "true");
        GetFollowUnfollowHandler handler = new GetFollowUnfollowHandler();
        FollowUnfollowResponse response = handler.handleRequest(request, null);
        System.out.println(response.getMessage());
        assertTrue(response.isSuccess());
    }

}