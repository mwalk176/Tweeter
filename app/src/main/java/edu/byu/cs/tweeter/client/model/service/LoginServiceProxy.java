package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import java.io.IOException;

public class LoginServiceProxy implements LoginService {

    private static final String URL_PATH = "/loginuser";

    private final ServerFacade serverFacade = new ServerFacade();


    public void setCurrentUser(User currentUser) {
        UserCache.getInstance().setCurrentlyLoggedInUser(currentUser);
        UserCache.getInstance().setCurrentlySelectedUser(currentUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) throws IOException {
        LoginResponse response = serverFacade.loginUser(request, URL_PATH);
        System.out.println("Login Response: " + response.toString());
        if(response.isSuccess()) setCurrentUser(response.getUser());
        return response;
    }
}
