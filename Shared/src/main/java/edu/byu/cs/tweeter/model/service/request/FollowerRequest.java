package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

/**
 * Contains all the information needed to make a request to have the server return the next page of
 * followees for a specified follower.
 */
public class FollowerRequest {

    private User followee;
    private int limit;
    private User lastFollower;
    private String authToken;

    private FollowerRequest() {}

    /**
     * Creates an instance.
     *
     * @param follower the {@link User} whose followees are to be returned.
     * @param limit the maximum number of followees to return.
     * @param lastFollowee the last followee that was returned in the previous request (null if
     *                     there was no previous request or if no followees were returned in the
     *                     previous request).
     */
    public FollowerRequest(User followee, int limit, User lastFollower, String authToken) {
        this.followee = followee;
        this.limit = limit;
        this.lastFollower = lastFollower;
        this.authToken = authToken;
    }

    /**
     * Returns the follower whose followees are to be returned by this request.
     *
     * @return the follower.
     */
    public User getFollowee() {
        return followee;
    }

    /**
     * Returns the number representing the maximum number of followees to be returned by this request.
     *
     * @return the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns the last followee that was returned in the previous request or null if there was no
     * previous request or if no followees were returned in the previous request.
     *
     * @return the last followee.
     */
    public User getLastFollower() {
        return lastFollower;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastFollower(User lastFollower) {
        this.lastFollower = lastFollower;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "FollowerRequest{" +
                "followee=" + followee +
                ", limit=" + limit +
                ", lastFollower=" + lastFollower +
                '}';
    }
}
