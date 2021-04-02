package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

import java.io.IOException;

public class LogoutServiceProxy implements LogoutService {

    private static final String URL_PATH = "/logoutuser";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public LogoutResponse logoutUser(LogoutRequest request) throws IOException {
        LogoutResponse response = serverFacade.logoutUser(request, URL_PATH);
        System.out.println("Logout Response: " + response.toString());
        return response;
    }
}
