package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetCheckFollowingHandlerTest {

    @Test
    void handleRequestTestSuccess() {
        User user1 = new User("Abraham", "Babbitt", "AbrahamBabbitt", "url");
        User user2 = new User("firstname2", "lastname2", "testUser", "url2");
        CheckIfFollowingRequest request = new CheckIfFollowingRequest(user1, user2, "");
        GetCheckFollowingHandler handler = new GetCheckFollowingHandler();
        CheckIfFollowingResponse response = handler.handleRequest(request, null);
        System.out.println(response.isSuccess());
        System.out.println(response.isFollowingUser());
        System.out.println(response.getMessage());
        assertFalse(response.isFollowingUser());
    }


}
