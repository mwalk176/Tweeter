package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;

import java.io.IOException;

public interface FollowUnfollowService {

    FollowUnfollowResponse followUnfollowUser(FollowUnfollowRequest request) throws IOException;

}
