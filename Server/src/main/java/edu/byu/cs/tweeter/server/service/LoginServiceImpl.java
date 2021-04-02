package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

import java.io.IOException;

public class LoginServiceImpl implements LoginService {
    @Override
    public LoginResponse loginUser(LoginRequest request) throws IOException {
        UserDAO dao = new UserDAO();
        LoginResponse response = dao.loginUser(request);

        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        String authToken = authTokenDAO.addAuthToken(request.getUsername());
        if(authToken == null) return new LoginResponse(false,
                "error occurred while generating authToken");

        response.setAuthToken(authToken);
        return response;
    }
}
