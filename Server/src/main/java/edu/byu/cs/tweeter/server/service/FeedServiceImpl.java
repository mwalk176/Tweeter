package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.FeedDAO;

import java.io.IOException;

public class FeedServiceImpl implements FeedService {
    @Override
    public FeedResponse getFeedStatuses(FeedRequest feedRequest) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(feedRequest.getAuthToken());
        if (stillValid) {
            FeedDAO dao = new FeedDAO();
            return dao.getFeedStatuses(feedRequest);
        } else {
            return new FeedResponse("Error, session expired! Please log in again");
        }

    }
}
