package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

import java.util.List;

/**
 * A paged response for a {@link edu.byu.cs.tweeter.model.service.request.FollowingRequest}.
 */
public class FollowerResponse extends PagedResponse {

    private List<User> followers;

    public FollowerResponse() {
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public FollowerResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param followees the followees to be included in the result.
     * @param hasMorePages an indicator or whether more data is available for the request.
     */
    public FollowerResponse(List<User> followers, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "FollowerResponse{" +
                "success='" + super.isSuccess() + '\'' +
                ", message='" + super.getMessage() + '\'' +
                ", followers=" + followers +
                ", hasMorePages=" + isMorePages() +
                '}';
    }
}
