package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.server.service.FollowerServiceImpl;

import java.io.IOException;


public class GetFollowerHandler implements RequestHandler<FollowerRequest, FollowerResponse> {

    @Override
    public FollowerResponse handleRequest(FollowerRequest input, Context context) {
        FollowerServiceImpl followerService = new FollowerServiceImpl();
        try {
            return followerService.getFollowers(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
