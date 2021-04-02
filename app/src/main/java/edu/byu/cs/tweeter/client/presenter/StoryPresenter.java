package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.StoryServiceProxy;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public class StoryPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public StoryPresenter(View view) {
        this.view = view;
    }

    public StoryResponse getStatuses(StoryRequest storyRequest) throws IOException {
        StoryServiceProxy proxy = new StoryServiceProxy();
        return proxy.getStatuses(storyRequest);
    }
}
