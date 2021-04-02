package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingServiceImpl implements FollowingService {

    @Override
    public FollowingResponse getFollowees(FollowingRequest request) {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(request.getAuthToken());
        if (stillValid) {
            FollowDAO followDAO = new FollowDAO();
            FollowingResponse response = followDAO.getFollowees(request);
            return response;
        } else {
            return new FollowingResponse("Error, session expired! Please log in again");
        }

    }
}
