package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.Status;

import java.util.List;

public class FeedResponse extends PagedResponse {

    List<Status> statuses;


    public FeedResponse(String message) {
        super(false, message, false);
    }

    public FeedResponse(List<Status> statuses, boolean hasMorePages) {
        super(true, hasMorePages);
        this.statuses = statuses;

    }

    public List<Status> getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "StoryResponse{" +
                "success=" + isSuccess() +
                ", statuses=" + statuses +
                ", message=" + getMessage() +
                ", morePages=" + isMorePages() +
                '}';
    }
}
