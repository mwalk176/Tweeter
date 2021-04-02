package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetStoryHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "testUser", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");
        StoryRequest request = new StoryRequest(user1, 10, null, "");
        GetStoryHandler handler = new GetStoryHandler();
        StoryResponse response = handler.handleRequest(request, null);
        System.out.println(response);
        assertTrue(response.isSuccess());
    }

}