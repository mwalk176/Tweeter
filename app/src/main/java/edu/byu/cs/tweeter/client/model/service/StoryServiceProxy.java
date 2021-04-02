package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public class StoryServiceProxy implements StoryService {

    private static final String URL_PATH = "/getstory";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public StoryResponse getStatuses(StoryRequest storyRequest) throws IOException {
        StoryResponse response = serverFacade.getStory(storyRequest, URL_PATH);
        System.out.println("Story Response: " + response.toString());
        return response;
    }
}
