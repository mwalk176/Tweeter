package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

import java.io.IOException;

public class LogoutTask extends AsyncTask<Void, Void, LogoutResponse> implements MainPresenter.View {

    private final LogoutObserver observer;
    private MainPresenter presenter;

    public interface LogoutObserver {
        void logoutSuccessful(LogoutResponse response);
        void logoutFailure(LogoutResponse response);
    }

    public LogoutTask(LogoutObserver observer) {
        this.observer = observer;
        presenter = new MainPresenter(this);
    }

    @Override
    protected LogoutResponse doInBackground(Void... voids) {
        LogoutResponse response = null;
        try {
            response = presenter.logOutUser();
        } catch (IOException e) {
            e.printStackTrace();
            response = new LogoutResponse(false, "Error occurred while trying to logout in async task");
        }
        return response;
    }

    @Override
    protected void onPostExecute(LogoutResponse logoutResponse) {
        if(logoutResponse.isSuccess()) {
            if(observer != null) {
                observer.logoutSuccessful(logoutResponse);
            }
        } else {
            if(observer != null) {
                observer.logoutFailure(logoutResponse);
            }
        }

    }
}
