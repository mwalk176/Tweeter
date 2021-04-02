package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetLoginHandlerTest {

    @Test
    void handleRequestTest() {
/*        User user1 = new User("firstname", "lastname", "alias", "url");
        User user2 = new User("firstname2", "lastname2", "alias2", "url2");*/

        LoginRequest request = new LoginRequest("Username", "Password");
        GetLoginHandler handler = new GetLoginHandler();
        LoginResponse response = handler.handleRequest(request, null);
        System.out.println(response.toString());
        assertNotNull(response.getAuthToken());
    }

}