package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

import java.io.IOException;

public class FollowerServiceImpl implements FollowerService {
    @Override
    public FollowerResponse getFollowers(FollowerRequest request) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(request.getAuthToken());
        if (stillValid) {
            FollowDAO followDAO = new FollowDAO();
            FollowerResponse response = followDAO.getFollowers(request);
            return response;
        } else {
            return new FollowerResponse("Error, session expired! Please log in again");
        }

    }
}
