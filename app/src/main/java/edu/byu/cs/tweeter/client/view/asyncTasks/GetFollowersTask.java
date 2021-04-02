package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import edu.byu.cs.tweeter.client.presenter.FollowerPresenter;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

import java.io.IOException;

/**
 * An {@link AsyncTask} for retrieving followees for a user.
 */
public class GetFollowersTask extends AsyncTask<FollowerRequest, Void, FollowerResponse> {

    private final FollowerPresenter presenter;
    private final GetFollowersObserver observer;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface GetFollowersObserver {
        void followersRetrieved(FollowerResponse followerResponse);
        void followersFailed(FollowerResponse followerResponse);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetFollowersTask(FollowerPresenter presenter, GetFollowersObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected FollowerResponse doInBackground(FollowerRequest... followerRequests) {
        FollowerResponse response = null;
        try {
            response = presenter.getFollowers(followerRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response != null && response.isSuccess()) {
            loadImages(response);

        }
        return response;
    }

    /**
     * Loads the image associated with each followee included in the response.
     *
     * @param response the response from the followee request.
     */
    private void loadImages(FollowerResponse response) {
        for(User user : response.getFollowers()) {

            Drawable drawable;

            try {
                drawable = ImageUtils.drawableFromUrl(user.getImageUrl());
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.toString(), e);
                drawable = null;
            }

            ImageCache.getInstance().cacheImage(user, drawable);
        }
    }

    @Override
    protected void onPostExecute(FollowerResponse followerResponse) {

        if(followerResponse.isSuccess() && followerResponse.getFollowers() != null) {
            if(observer != null) {
                observer.followersRetrieved(followerResponse);
            }
        } else {
                observer.followersFailed(followerResponse);
        }


    }
}
