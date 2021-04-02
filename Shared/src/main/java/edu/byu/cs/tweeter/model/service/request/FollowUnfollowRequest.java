package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowUnfollowRequest {

    boolean isAlreadyFollowing;
    User loggedInUser;
    User selectedUser;
    String authToken;
    String isFollowing;

    public FollowUnfollowRequest() {
    }

    public FollowUnfollowRequest(boolean isAlreadyFollowing, User loggedInUser,
                                 User selectedUser, String authToken, String isFollowing) {
        this.isAlreadyFollowing = isAlreadyFollowing;
        this.loggedInUser = loggedInUser;
        this.selectedUser = selectedUser;
        this.authToken = authToken;
        this.isFollowing = isFollowing;
    }

    public boolean isAlreadyFollowing() {
        return isAlreadyFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        isAlreadyFollowing = isFollowing;
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

    public String getFollowing() {
        return isFollowing;
    }

    public void setFollowing(String isFollowing) {
        this.isFollowing = isFollowing;
    }

    @Override
    public String toString() {
        return "FollowUnfollowRequest{" +
                "isAlreadyFollowing=" + isAlreadyFollowing +
                ", loggedInUser=" + loggedInUser +
                ", selectedUser=" + selectedUser +
                ", authToken='" + authToken +
                ", alreadyFollowing='" + isFollowing +
                '}';
    }
}
