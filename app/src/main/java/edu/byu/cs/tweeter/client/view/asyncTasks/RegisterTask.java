package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.RegisterPresenter;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

import java.io.IOException;

public class RegisterTask extends AsyncTask<RegisterRequest, Integer, RegisterResponse> implements RegisterPresenter.View {


    private final RegisterTask.RegisterObserver observer;
    private RegisterPresenter presenter;

    public interface RegisterObserver {
        void registerSuccessful(RegisterResponse response);
        void registerFailure(RegisterResponse response);
    }

    public RegisterTask(RegisterTask.RegisterObserver observer) {
        this.observer = observer;
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected RegisterResponse doInBackground(RegisterRequest... requests) {
        try {
            RegisterResponse response = presenter.registerUser(requests[0]);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new RegisterResponse(false, "Error occurred while trying to register in async task");
        }

    }

    @Override
    protected void onPostExecute(RegisterResponse response) {
        if(response.isSuccess()) {
            if(observer != null) {
                UserCache.getInstance().setAuthToken(response.getAuthToken());
                observer.registerSuccessful(response);
            }
        } else {
            if(observer != null) {
                observer.registerFailure(response);
            }
        }

    }
}
