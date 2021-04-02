package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

import java.io.IOException;

public class RegisterServiceImpl implements RegisterService {
    @Override
    public RegisterResponse registerUser(RegisterRequest request) throws IOException {
        UserDAO dao = new UserDAO();
        RegisterResponse response = dao.registerUser(request);

        AuthTokenDAO authTokenDAO = new AuthTokenDAO();
        String authToken = authTokenDAO.addAuthToken(request.getAlias());
        if(authToken == null) return new RegisterResponse(false,
                "error occurred while generating authToken");

        response.setAuthToken(authToken);
        return response;
    }
}
