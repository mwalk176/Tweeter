package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryRequest  {

    private User associatedUser;
    private int limit;
    private Status lastStatus;
    private String authToken;

    public StoryRequest() {}

    public StoryRequest(User associatedUser, int limit, Status lastStatus, String authToken) {
        this.associatedUser = associatedUser;
        this.limit = limit;
        this.lastStatus = lastStatus;
        this.authToken = authToken;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public int getLimit() {
        return limit;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
