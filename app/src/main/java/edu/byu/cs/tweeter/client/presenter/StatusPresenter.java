package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.StatusServiceProxy;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

import java.io.IOException;

public class StatusPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public StatusPresenter(View view) {
        this.view = view;
    }

    public PostStatusResponse postStatus(PostStatusRequest request) throws IOException {
        StatusServiceProxy proxy = new StatusServiceProxy();
        return proxy.postStatus(request);

    }
}
