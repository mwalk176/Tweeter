package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.LogoutServiceProxy;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

import java.io.IOException;

/**
 * The presenter for the main activity.
 */
public class MainPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter sends notifications to it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public MainPresenter(View view) {
        this.view = view;
    }

    @Override
    public User getCurrentUser() {
        return UserCache.getInstance().getCurrentlyLoggedInUser();
    }

    public LogoutResponse logOutUser() throws IOException {

        LogoutServiceProxy proxy = new LogoutServiceProxy();
        LogoutRequest request = new LogoutRequest(UserCache.getInstance().getAuthToken(),
                UserCache.getInstance().getCurrentlyLoggedInUser().getAlias());
        LogoutResponse response = proxy.logoutUser(request);
        if(response.isSuccess()) {
            UserCache.getInstance().setCurrentlyLoggedInUser(null);
            UserCache.getInstance().setCurrentlySelectedUser(null);
        }
        return response;
    }
}
