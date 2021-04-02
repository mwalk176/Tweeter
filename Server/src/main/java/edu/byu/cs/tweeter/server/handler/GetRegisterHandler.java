package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.service.RegisterServiceImpl;

import java.io.IOException;

public class GetRegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {
    @Override
    public RegisterResponse handleRequest(RegisterRequest input, Context context) {
        RegisterServiceImpl registerService = new RegisterServiceImpl();
        try {
            return registerService.registerUser(input);
        } catch (IOException e) {
            e.printStackTrace();
            return new RegisterResponse(false, "error occurred while in register handler");
        }
    }
}
