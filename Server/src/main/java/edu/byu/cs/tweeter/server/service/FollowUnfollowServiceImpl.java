package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowUnfollowService;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

import java.io.IOException;

public class FollowUnfollowServiceImpl implements FollowUnfollowService {
    @Override
    public FollowUnfollowResponse followUnfollowUser(FollowUnfollowRequest request) throws IOException {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean stillValid = authTokenDAO.validateToken(request.getAuthToken());
        if (stillValid) {
            FollowDAO followDao = new FollowDAO();
            FollowUnfollowResponse response = followDao.followUnfollowUser(request);
            return response;
        } else {
            return new FollowUnfollowResponse(false,
                    "Error, session expired! Please log in again");
        }

    }
}
