package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.FeedServiceProxy;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.io.IOException;

public class FeedPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public FeedPresenter(View view) {
        this.view = view;
    }

    public FeedResponse getFeedStatuses(FeedRequest feedRequest) throws IOException {
        FeedServiceProxy proxy = new FeedServiceProxy();
        return proxy.getFeedStatuses(feedRequest);
    }
}
