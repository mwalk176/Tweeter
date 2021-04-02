package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.User;

import java.util.List;

/**
 * A paged response for a {@link edu.byu.cs.tweeter.model.service.request.FollowingRequest}.
 */
public class FollowingResponse extends PagedResponse {

    private List<User> followees;

    public FollowingResponse() {
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public FollowingResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param followees the followees to be included in the result.
     * @param hasMorePages an indicator or whether more data is available for the request.
     */
    public FollowingResponse(List<User> followees, boolean hasMorePages) {
        super(true, hasMorePages);
        setMorePages(hasMorePages);
        this.followees = followees;

    }

    public List<User> getFollowees() {
        return followees;
    }

    public void setFollowees(List<User> followees) {
        this.followees = followees;
    }

    @Override
    public String toString() {
        return "FollowingResponse{" +
                "followees=" + followees +
                ", hasMorePages=" + isMorePages() +
                ", success=" + isSuccess() +
                ", message='" + getMessage() + '\'' +
                '}';
    }


}
