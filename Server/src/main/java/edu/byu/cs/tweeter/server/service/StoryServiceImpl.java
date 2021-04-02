package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

import java.io.IOException;

public class StoryServiceImpl implements StoryService {
    @Override
    public StoryResponse getStatuses(StoryRequest storyRequest) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(storyRequest.getAuthToken());
        if (stillValid) {
            StoryDAO dao = new StoryDAO();
            return dao.getStatuses(storyRequest);
        } else {
            return new StoryResponse("Error, session expired! Please log in again");
        }

    }
}
