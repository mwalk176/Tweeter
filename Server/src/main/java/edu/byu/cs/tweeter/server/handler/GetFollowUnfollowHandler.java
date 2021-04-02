package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;
import edu.byu.cs.tweeter.server.service.FollowUnfollowServiceImpl;

import java.io.IOException;

public class GetFollowUnfollowHandler implements RequestHandler<FollowUnfollowRequest, FollowUnfollowResponse> {
    @Override
    public FollowUnfollowResponse handleRequest(FollowUnfollowRequest input, Context context) {
        FollowUnfollowServiceImpl followUnfollowService = new FollowUnfollowServiceImpl();
        try {
            return followUnfollowService.followUnfollowUser(input);
        } catch (IOException e) {
            e.printStackTrace();
            return new FollowUnfollowResponse(false, "error occurred in handler");
        }
    }
}
