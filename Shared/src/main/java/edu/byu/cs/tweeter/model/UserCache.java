package edu.byu.cs.tweeter.model;

import edu.byu.cs.tweeter.model.domain.User;

public class UserCache {

    private static UserCache instance;

    private User currentlyLoggedInUser;
    private User currentlySelectedUser;
    private String authToken;
    private Boolean isAlreadyFollowing;

    public static UserCache getInstance() {
        if(instance == null) {
            instance = new UserCache();
        }

        return instance;
    }

    private UserCache() {
        currentlyLoggedInUser = null;
        currentlySelectedUser = null;
        isAlreadyFollowing = false;
    }

    public User getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    public User getCurrentlySelectedUser() {
        return currentlySelectedUser;
    }

    public void setCurrentlyLoggedInUser(User currentlyLoggedInUser) {
        this.currentlyLoggedInUser = currentlyLoggedInUser;
    }

    public void setCurrentlySelectedUser(User currentlySelectedUser) {
        this.currentlySelectedUser = currentlySelectedUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Boolean getAlreadyFollowing() {
        return isAlreadyFollowing;
    }

    public void setAlreadyFollowing(Boolean alreadyFollowing) {
        isAlreadyFollowing = alreadyFollowing;
    }
}
