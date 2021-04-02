package edu.byu.cs.tweeter.model.service.request;

public class LogoutRequest {

    private String authToken;
    private String associatedUsername;

    public LogoutRequest() {
    }

    public LogoutRequest(String authToken, String associatedUsername) {
        this.authToken = authToken;
        this.associatedUsername = associatedUsername;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    @Override
    public String toString() {
        return "LogoutRequest{" +
                "authToken='" + authToken + '\'' +
                ", associatedUsername='" + associatedUsername + '\'' +
                '}';
    }
}
