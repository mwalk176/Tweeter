package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.CheckFollowingService;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

import java.io.IOException;

public class CheckFollowingServiceImpl implements CheckFollowingService {
    @Override
    public CheckIfFollowingResponse checkIfFollowing(CheckIfFollowingRequest request) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(request.getAuthToken());
        if (stillValid) {
            FollowDAO followDAO = new FollowDAO();
            CheckIfFollowingResponse response = followDAO.checkIfFollowing(request);
            return response;
        } else {
            return new CheckIfFollowingResponse(false, "Error, session expired! Please log in again");
        }
    }
}
