package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.UserPagePresenter;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;

import java.io.IOException;

public class FollowUnfollowTask extends AsyncTask<FollowUnfollowRequest, Integer, FollowUnfollowResponse>
        implements UserPagePresenter.View {

    private final FollowUnfollowObserver observer;
    private UserPagePresenter presenter;

    public interface FollowUnfollowObserver {
        void followUnfollowSuccessful(FollowUnfollowResponse followUnfollowResponse);
        void followUnfollowFailure(FollowUnfollowResponse followUnfollowResponse);
    }

    public FollowUnfollowTask(FollowUnfollowObserver observer) {
        this.observer = observer;
        presenter = new UserPagePresenter(this);
    }

    @Override
    protected FollowUnfollowResponse doInBackground(FollowUnfollowRequest... followUnfollowRequests) {
        try {
            FollowUnfollowResponse response = presenter.followUnfollowUser(followUnfollowRequests[0]);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new FollowUnfollowResponse(false, "error occurred in async task");
        }
    }

    @Override
    protected void onPostExecute(FollowUnfollowResponse followUnfollowResponse) {
        if(followUnfollowResponse.isSuccess()) {
            if(observer != null) {
                observer.followUnfollowSuccessful(followUnfollowResponse);
            }
        } else {
            if(observer != null) {
                observer.followUnfollowFailure(followUnfollowResponse);
            }
        }
    }
}
