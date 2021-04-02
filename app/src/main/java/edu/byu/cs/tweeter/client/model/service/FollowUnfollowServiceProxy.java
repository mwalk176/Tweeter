package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FollowUnfollowService;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;

import java.io.IOException;

public class FollowUnfollowServiceProxy implements FollowUnfollowService {


    private static final String URL_PATH = "/followunfollowuser";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FollowUnfollowResponse followUnfollowUser(FollowUnfollowRequest request) throws IOException {
        FollowUnfollowResponse response = serverFacade.followUnfollowUser(request, URL_PATH);
        System.out.println("Follow Unfollow Response: " + response.toString());
        return response;

    }
}
