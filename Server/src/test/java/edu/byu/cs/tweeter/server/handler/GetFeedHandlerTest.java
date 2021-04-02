package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetFeedHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "testUser", "url");
        FeedRequest request = new FeedRequest(user1, 10, null, "");
        GetFeedHandler handler = new GetFeedHandler();
        FeedResponse response = handler.handleRequest(request, null);
        System.out.println(response);
        assertNotNull(response);
    }
}
