package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.StatusPresenter;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

import java.io.IOException;

public class PostStatusTask extends AsyncTask<PostStatusRequest, Integer, PostStatusResponse> implements StatusPresenter.View {

    private final PostStatusObserver observer;
    private StatusPresenter presenter;

    public interface PostStatusObserver {
        void postStatusSuccessful(PostStatusResponse response);
        void postStatusFailure(PostStatusResponse response);
    }

    public PostStatusTask(PostStatusObserver observer) {
        this.observer = observer;
        presenter = new StatusPresenter(this);
    }

    @Override
    protected PostStatusResponse doInBackground(PostStatusRequest... postStatusRequests) {
        try {
            PostStatusResponse response = presenter.postStatus(postStatusRequests[0]);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new PostStatusResponse(false, "error occurred while trying to post status in async");
        }
    }

    @Override
    protected void onPostExecute(PostStatusResponse postStatusResponse) {
        if(postStatusResponse.isSuccess()) {
            if(observer != null) {
                observer.postStatusSuccessful(postStatusResponse);
            }
        } else {
            if(observer != null) {
                observer.postStatusFailure(postStatusResponse);
            }
        }
    }
}
