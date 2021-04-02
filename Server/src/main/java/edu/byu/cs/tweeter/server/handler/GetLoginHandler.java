package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.service.LoginServiceImpl;

import java.io.IOException;

public class GetLoginHandler implements RequestHandler<LoginRequest, LoginResponse> {


    @Override
    public LoginResponse handleRequest(LoginRequest input, Context context) {
        LoginServiceImpl loginService = new LoginServiceImpl();
        try {
            return loginService.loginUser(input);
        } catch (IOException e) {
            e.printStackTrace();
            return new LoginResponse(false, "Error occured while handling login request");
        }
    }
}
