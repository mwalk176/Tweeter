package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

import java.io.IOException;

public interface LogoutService {

    LogoutResponse logoutUser(LogoutRequest request) throws IOException;
}
