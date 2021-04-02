package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import edu.byu.cs.tweeter.server.service.CheckFollowingServiceImpl;

import java.io.IOException;

public class GetCheckFollowingHandler implements RequestHandler<CheckIfFollowingRequest, CheckIfFollowingResponse> {
    @Override
    public CheckIfFollowingResponse handleRequest(CheckIfFollowingRequest input, Context context) {
        CheckFollowingServiceImpl checkFollowingService = new CheckFollowingServiceImpl();
        try {
            return checkFollowingService.checkIfFollowing(input);
        } catch (IOException e) {
            e.printStackTrace();
            return new CheckIfFollowingResponse(false, "error occurred in handler of check followign");
        }
    }
}
