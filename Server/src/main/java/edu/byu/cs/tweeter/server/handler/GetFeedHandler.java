package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.service.FeedServiceImpl;

import java.io.IOException;

public class GetFeedHandler implements RequestHandler<FeedRequest, FeedResponse> {

    @Override
    public FeedResponse handleRequest(FeedRequest input, Context context) {
        FeedServiceImpl feedService = new FeedServiceImpl();
        try {
            return feedService.getFeedStatuses(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
