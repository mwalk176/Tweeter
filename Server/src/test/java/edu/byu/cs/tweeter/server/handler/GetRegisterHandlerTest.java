package edu.byu.cs.tweeter.server.handler;

import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetRegisterHandlerTest {

    @Test
    void handleRequestTest() {
//        User user1 = new User("firstname", "lastname", "alias", "url");
//        User user2 = new User("firstname2", "lastname2", "alias2", "url2");

        RegisterRequest request = new RegisterRequest("firstname", "lastname",
                "Username", "Password");
        GetRegisterHandler handler = new GetRegisterHandler();
        RegisterResponse response = handler.handleRequest(request, null);
        System.out.println(response.toString());
        assertTrue(response.isSuccess());
    }

}
