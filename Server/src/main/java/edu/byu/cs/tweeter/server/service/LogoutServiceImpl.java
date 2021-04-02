package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;

import java.io.IOException;

public class LogoutServiceImpl implements LogoutService {
    @Override
    public LogoutResponse logoutUser(LogoutRequest request) throws IOException {

        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        boolean success = authTokenDAO.invalidateToken(request.getAuthToken(),
                request.getAssociatedUsername());
        if(success) {
            return new LogoutResponse(true);
        } else {
            return new LogoutResponse(false, "Error occurred while logging out");
        }
    }
}
