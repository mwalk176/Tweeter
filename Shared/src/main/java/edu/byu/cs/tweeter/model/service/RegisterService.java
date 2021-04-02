package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

import java.io.IOException;

public interface RegisterService {

    RegisterResponse registerUser(RegisterRequest request) throws IOException;
}
