package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.StatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

import java.io.IOException;

public class StatusServiceProxy implements StatusService {

    private static final String URL_PATH = "/poststatus";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public PostStatusResponse postStatus(PostStatusRequest request) throws IOException {
        PostStatusResponse response = serverFacade.postStatus(request, URL_PATH);
        System.out.println("Post Status Response: " + response.toString());
        return response;

    }
}
