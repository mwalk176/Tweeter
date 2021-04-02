package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import java.io.IOException;

/**
 * A remote-access proxy for accessing the 'following' service.
 */
public class FollowingServiceProxy implements FollowingService {

    private static final String URL_PATH = "/getfollowing";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FollowingResponse getFollowees(FollowingRequest request) throws IOException {
        System.out.println("Sending a Following Request: " + request.toString());
        FollowingResponse response = serverFacade.getFollowees(request, URL_PATH);
        System.out.println("Following Response: " + response.toString());
        return response;
    }
}
