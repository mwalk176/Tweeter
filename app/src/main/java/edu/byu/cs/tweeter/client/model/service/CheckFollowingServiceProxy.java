package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.CheckFollowingService;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;

import java.io.IOException;

public class CheckFollowingServiceProxy implements CheckFollowingService {


    private static final String URL_PATH = "/checkfollowing";

    private final ServerFacade serverFacade = new ServerFacade();


    @Override
    public CheckIfFollowingResponse checkIfFollowing(CheckIfFollowingRequest request) throws IOException {
        CheckIfFollowingResponse response = serverFacade.checkIfFollowing(request, URL_PATH);
        System.out.println("Check If Following Response: " + response.toString());
        return response;
    }
}
