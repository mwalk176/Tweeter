package edu.byu.cs.tweeter.model.service.response;

public class FollowUnfollowResponse extends Response {
    public FollowUnfollowResponse(boolean success) {
        super(success);
    }

    public FollowUnfollowResponse(boolean success, String message) {
        super(success, message);
    }


}
