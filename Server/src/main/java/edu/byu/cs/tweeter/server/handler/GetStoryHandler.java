package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.service.StoryServiceImpl;

import java.io.IOException;

public class GetStoryHandler implements RequestHandler<StoryRequest, StoryResponse> {


    @Override
    public StoryResponse handleRequest(StoryRequest input, Context context) {
        StoryServiceImpl storyService = new StoryServiceImpl();
        try {
            return storyService.getStatuses(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
