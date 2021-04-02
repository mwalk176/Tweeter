package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import edu.byu.cs.tweeter.client.presenter.FeedPresenter;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.io.IOException;

public class GetFeedTask extends AsyncTask<FeedRequest, Void, FeedResponse> {

    private final FeedPresenter presenter;
    private final GetFeedObserver observer;

    public interface GetFeedObserver {
        void feedStatusesRetrieved(FeedResponse feedResponse);
        void feedStatusesFailed(FeedResponse feedResponse);
    }

    public GetFeedTask(FeedPresenter presenter, GetFeedObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected FeedResponse doInBackground(FeedRequest... feedRequests) {
        FeedResponse response = null;
        try {
            response = presenter.getFeedStatuses(feedRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response != null && response.getStatuses() != null) {
            loadImages(response);
            return response;
        } else {
            return new FeedResponse("unexpected error occurred in async");
        }


    }

    private void loadImages(FeedResponse response) {
        for(edu.byu.cs.tweeter.model.domain.Status status : response.getStatuses()) {

            Drawable drawable;

            try {
                drawable = ImageUtils.drawableFromUrl(status.getAssociatedUser().getImageUrl());
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.toString(), e);
                drawable=null;
            }

            ImageCache.getInstance().cacheImage(status.getAssociatedUser(), drawable);
        }
    }

    @Override
    protected void onPostExecute(FeedResponse feedResponse) {
        if(feedResponse != null && feedResponse.getStatuses() != null) {
            if(feedResponse.isSuccess()) {
                if(observer != null) {
                    observer.feedStatusesRetrieved(feedResponse);
                }
            } else {
                if(observer != null) {
                    observer.feedStatusesFailed(feedResponse);
                }
            }

        }

    }
}
