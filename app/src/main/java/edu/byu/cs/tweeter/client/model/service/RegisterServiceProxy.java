package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

import java.io.IOException;

public class RegisterServiceProxy implements RegisterService {

    private static final String URL_PATH = "/registeruser";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public RegisterResponse registerUser(RegisterRequest request) throws IOException {
        RegisterResponse response = serverFacade.registerUser(request, URL_PATH);
        System.out.println("Register Response: " + response.toString());
        if(response.isSuccess()) {
            UserCache.getInstance().setCurrentlyLoggedInUser(response.getUser());
            UserCache.getInstance().setCurrentlySelectedUser(response.getUser());
        }
        return response;
    }
}
