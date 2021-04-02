package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetLogoutHandlerTest {

    @Test
    void handleRequestTest() {
        User user1 = new User("firstname", "lastname", "alias", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");

        LogoutRequest request = new LogoutRequest("6063850f-8332-4a23-832d-75ffad1fa826", "@UserAlias");

        GetLogoutHandler handler = new GetLogoutHandler();
        LogoutResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());
    }

}
