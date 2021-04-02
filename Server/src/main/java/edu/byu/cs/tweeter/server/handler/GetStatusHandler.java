package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.server.service.StatusServiceImpl;

import java.io.IOException;

public class GetStatusHandler implements RequestHandler<PostStatusRequest, PostStatusResponse> {
    @Override
    public PostStatusResponse handleRequest(PostStatusRequest input, Context context) {
        StatusServiceImpl statusService = new StatusServiceImpl();
        try {
            return statusService.postStatus(input);
        } catch (IOException e) {
            e.printStackTrace();
            return new PostStatusResponse(false, "error occurred while trying to post status in handler");
        }
    }
}
