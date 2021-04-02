package edu.byu.cs.tweeter.server.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.service.LogoutServiceImpl;

import java.io.IOException;

public class GetLogoutHandler implements RequestHandler<LogoutRequest, LogoutResponse> {
    @Override
    public LogoutResponse handleRequest(LogoutRequest request, Context context) {
        LogoutServiceImpl logoutService = new LogoutServiceImpl();
        try {
            return logoutService.logoutUser(request);
        } catch (IOException e) {
            e.printStackTrace();
            return new LogoutResponse(false, "Error occurred in logout handler");
        }
    }
}
