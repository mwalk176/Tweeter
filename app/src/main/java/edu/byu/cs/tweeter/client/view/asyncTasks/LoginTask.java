package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import java.io.IOException;

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResponse> implements LoginPresenter.View {

    private final LoginObserver observer;
    private LoginPresenter presenter;

    public interface LoginObserver {
        void loginSuccessful(LoginResponse response);
        void loginFailure(LoginResponse response);
    }

    public LoginTask(LoginObserver observer) {
        this.observer = observer;
        presenter = new LoginPresenter(this);
    }

    @Override
    protected LoginResponse doInBackground(LoginRequest... requests) {
        try {
            LoginResponse response = presenter.loginUser(requests[0]);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new LoginResponse(false, "Error occurred while trying to login user via async");
        }

    }

    @Override
    protected void onPostExecute(LoginResponse response) {
        if(response.isSuccess()) {
            if(observer != null) {
                UserCache.getInstance().setAuthToken(response.getAuthToken());
                observer.loginSuccessful(response);
            }
        } else {
            if(observer != null) {
                observer.loginFailure(response);
            }
        }

    }
}

