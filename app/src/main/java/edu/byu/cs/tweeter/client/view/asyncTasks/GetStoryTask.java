package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import edu.byu.cs.tweeter.client.presenter.StoryPresenter;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public class GetStoryTask extends AsyncTask<StoryRequest, Void, StoryResponse> {

    private final StoryPresenter presenter;
    private final GetStoryObserver observer;

    public interface GetStoryObserver {
        void statusesRetrieved(StoryResponse storyResponse);
        void statusesFailed(StoryResponse storyResponse);
    }

    public GetStoryTask(StoryPresenter presenter, GetStoryObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected StoryResponse doInBackground(StoryRequest... storyRequests) {
        StoryResponse response = null;
        try {
            response = presenter.getStatuses(storyRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response != null && response.isSuccess()) {
            loadImages(response);

        }
        return response;
    }

    private void loadImages(StoryResponse response) {
        for(edu.byu.cs.tweeter.model.domain.Status status : response.getStatuses()) {

            Drawable drawable;

            try {
                drawable = ImageUtils.drawableFromUrl(status.getAssociatedUser().getImageUrl());
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.toString(), e);
                drawable = null;
            }

            ImageCache.getInstance().cacheImage(status.getAssociatedUser(), drawable);
        }
    }

    @Override
    protected void onPostExecute(StoryResponse storyResponse) {
        if (storyResponse.isSuccess()) {
            if(observer != null) {
                observer.statusesRetrieved(storyResponse);
            }
        } else {
            if(observer != null) {
                observer.statusesFailed(storyResponse);
            }
        }

    }
}
