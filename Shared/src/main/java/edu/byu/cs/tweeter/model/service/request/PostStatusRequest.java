package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class PostStatusRequest {

    String message;
    User associatedUser;
    String authToken;

    public PostStatusRequest() {
    }

    public PostStatusRequest(String message, User associatedUser, String authToken) {
        this.message = message;
        this.associatedUser = associatedUser;
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "PostStatusRequest{" +
                "message='" + message + '\'' +
                ", associatedUser=" + associatedUser +
                '}';
    }
}
