package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class CheckIfFollowingRequest {

    User loggedInUser;
    User selectedUser;
    String authToken;

    public CheckIfFollowingRequest() {
    }

    public CheckIfFollowingRequest(User loggedInUser, User selectedUser, String authToken) {
        this.loggedInUser = loggedInUser;
        this.selectedUser = selectedUser;
        this.authToken = authToken;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "CheckIfFollowingRequest{" +
                "loggedInUser=" + loggedInUser +
                ", selectedUser=" + selectedUser +
                '}';
    }
}
