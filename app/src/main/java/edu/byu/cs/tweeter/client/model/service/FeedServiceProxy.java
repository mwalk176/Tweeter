package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.io.IOException;

public class FeedServiceProxy implements FeedService {

    private static final String URL_PATH = "/getfeed";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FeedResponse getFeedStatuses(FeedRequest feedRequest) throws IOException {
        FeedResponse response = serverFacade.getFeed(feedRequest, URL_PATH);
        System.out.println("Feed Response: " + response.toString());
        return response;
    }
}
