package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.CheckFollowingServiceProxy;
import edu.byu.cs.tweeter.client.model.service.FollowUnfollowServiceProxy;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;

import java.io.IOException;

public class UserPagePresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public UserPagePresenter(View view) {
        this.view = view;
    }

    public void setSelectedUser(User user) {
        UserCache.getInstance().setCurrentlySelectedUser(user);
    }

    public void resetSelectedUser() {
        UserCache.getInstance().setCurrentlySelectedUser(UserCache.getInstance().getCurrentlyLoggedInUser());
    }

    public CheckIfFollowingResponse checkIfFollowing(CheckIfFollowingRequest request) throws IOException {
        CheckFollowingServiceProxy proxy = new CheckFollowingServiceProxy();
        return proxy.checkIfFollowing(request);
    }

    public FollowUnfollowResponse followUnfollowUser(FollowUnfollowRequest request) throws IOException {
        FollowUnfollowServiceProxy proxy = new FollowUnfollowServiceProxy();
        return proxy.followUnfollowUser(request);
    }
}
