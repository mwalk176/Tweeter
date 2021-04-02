package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.UserPagePresenter;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;

import java.io.IOException;

public class CheckFollowingTask extends AsyncTask<CheckIfFollowingRequest, Integer, CheckIfFollowingResponse>
        implements UserPagePresenter.View {

    private final CheckFollowingObserver observer;
    private UserPagePresenter presenter;

    public interface CheckFollowingObserver {
        void checkFollowingSuccessful(CheckIfFollowingResponse checkIfFollowingResponse);
        void checkFollowingFailure(CheckIfFollowingResponse checkIfFollowingResponse);
    }

    public CheckFollowingTask(CheckFollowingObserver observer) {
        this.observer = observer;
        presenter = new UserPagePresenter(this);
    }

    @Override
    protected CheckIfFollowingResponse doInBackground(CheckIfFollowingRequest... checkIfFollowingRequests) {
        try {
            System.out.println(checkIfFollowingRequests[0]);
            CheckIfFollowingResponse response = presenter.checkIfFollowing(checkIfFollowingRequests[0]);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new CheckIfFollowingResponse(false, "error occurred in async task");
        }
    }

    @Override
    protected void onPostExecute(CheckIfFollowingResponse checkIfFollowingResponse) {
        if(checkIfFollowingResponse.isSuccess()) {
            if(observer != null) {
                observer.checkFollowingSuccessful(checkIfFollowingResponse);
            }
        } else {
            if(observer != null) {
                observer.checkFollowingFailure(checkIfFollowingResponse);
            }
        }
    }
}
