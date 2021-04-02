package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

public class LoginResponse extends Response {

    private String authToken;
    private User user;

    public LoginResponse(boolean success, String message) {
        super(success, message);
        authToken = null;
        user = null;
    }

    public LoginResponse(String authToken, User user) {
        super(true, null);
        this.authToken = authToken;
        this.user = user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "success='" + super.isSuccess() + '\'' +
                ", message='" + super.getMessage() + '\'' +
                ", authToken='" + authToken + '\'' +
                ", user=" + user +
                '}';
    }
}
