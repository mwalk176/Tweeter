package edu.byu.cs.tweeter.model.service.response;

public class CheckIfFollowingResponse extends Response {

    boolean isFollowingUser;

    public CheckIfFollowingResponse(boolean success, boolean isFollowingUser) {
        super(success);
        this.isFollowingUser = isFollowingUser;
    }

    public CheckIfFollowingResponse(boolean success, String message) {
        super(success, message);
    }

    public boolean isFollowingUser() {
        return isFollowingUser;
    }

    @Override
    public String toString() {
        return "CheckIfFollowingResponse{" +
                "success=" + isSuccess() +
                ", isFollowingUser=" + isFollowingUser +
                ", message=" + getMessage() +
                '}';
    }
}
