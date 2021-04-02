package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

import java.io.IOException;

public class FollowerServiceProxy implements FollowerService {

    private static final String URL_PATH = "/getfollowers";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FollowerResponse getFollowers(FollowerRequest request) throws IOException {
        System.out.println("Sending a Follower Request: " + request.toString());
        FollowerResponse response = serverFacade.getFollowers(request, URL_PATH);
        System.out.println("Follower Response: " + response.toString());
        return response;
    }
}
