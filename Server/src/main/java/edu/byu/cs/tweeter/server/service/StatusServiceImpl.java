package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.StatusService;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

import java.io.IOException;

public class StatusServiceImpl implements StatusService {
    @Override
    public PostStatusResponse postStatus(PostStatusRequest request) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(request.getAuthToken());
        if (stillValid) {
            StoryDAO dao = new StoryDAO();
            return dao.postStatus(request);
        } else {
            return new PostStatusResponse(false,
                    "Error, session expired! Please log in again");
        }

    }
}
