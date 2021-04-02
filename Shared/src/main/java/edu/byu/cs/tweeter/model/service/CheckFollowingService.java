package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;

import java.io.IOException;

public interface CheckFollowingService {

    CheckIfFollowingResponse checkIfFollowing(CheckIfFollowingRequest request) throws IOException;

}
