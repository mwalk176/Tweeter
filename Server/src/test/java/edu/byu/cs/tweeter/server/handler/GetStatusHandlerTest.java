package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetStatusHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "alias", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");
        PostStatusRequest request = new PostStatusRequest("test", user1, "");
        GetStatusHandler handler = new GetStatusHandler();
        PostStatusResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());
    }

}