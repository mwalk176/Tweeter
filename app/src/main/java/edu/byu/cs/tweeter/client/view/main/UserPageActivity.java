package edu.byu.cs.tweeter.client.view.main;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.UserPagePresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.CheckFollowingTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.FollowUnfollowTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;

public class UserPageActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver, UserPagePresenter.View,
        CheckFollowingTask.CheckFollowingObserver, FollowUnfollowTask.FollowUnfollowObserver {

    final CheckFollowingTask.CheckFollowingObserver observer = this;
    final FollowUnfollowTask.FollowUnfollowObserver followUnfollowObserver = this;

    private UserPagePresenter presenter;
    private User user;
    private ImageView userImageView;
    private Button followUnfollowButton;
    boolean isFollowingPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new UserPagePresenter(this);

        followUnfollowButton = (Button) findViewById(R.id.followUnfollowButton);

        if(UserCache.getInstance().getCurrentlySelectedUser() == UserCache.getInstance().getCurrentlyLoggedInUser()) {
            followUnfollowButton.setVisibility(View.INVISIBLE);
        } else {
            if(UserCache.getInstance().getAlreadyFollowing()) {
                isFollowingPerson = true;
                followUnfollowButton.setText("Unfollow");

                followUnfollowButton.getBackground().setAlpha(255);
                followUnfollowButton.setBackgroundColor(Color.GRAY);
                followUnfollowButton.setClickable(true);
            } else {
                followUnfollowButton.getBackground().setAlpha(50);
                followUnfollowButton.setBackgroundColor(Color.LTGRAY);
                followUnfollowButton.setClickable(false);
                followUnfollowButton.setText("One Moment...");

                CheckFollowingTask checkFollowingTask = new CheckFollowingTask(observer);
                CheckIfFollowingRequest request = new CheckIfFollowingRequest(
                        UserCache.getInstance().getCurrentlyLoggedInUser(),
                        UserCache.getInstance().getCurrentlySelectedUser(),
                        UserCache.getInstance().getAuthToken());
                checkFollowingTask.execute(request);
            }


        }

        followUnfollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                followUnfollowButton.getBackground().setAlpha(50);
                followUnfollowButton.setBackgroundColor(Color.LTGRAY);
                followUnfollowButton.setClickable(false);
                followUnfollowButton.setText("One Moment...");

                FollowUnfollowTask followUnfollowTask = new FollowUnfollowTask(followUnfollowObserver);

                FollowUnfollowRequest request = new FollowUnfollowRequest(isFollowingPerson,
                        UserCache.getInstance().getCurrentlyLoggedInUser(),
                        UserCache.getInstance().getCurrentlySelectedUser(),
                        UserCache.getInstance().getAuthToken(),
                        Boolean.toString(isFollowingPerson));

                System.out.println("sending a followUnfollowRequest: " + request.toString());
                followUnfollowTask.execute(request);
            }
        });


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.userPageView_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.userPageTabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.userPageFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostStatusDialog postStatusDialog = new PostStatusDialog();
                postStatusDialog.show(getSupportFragmentManager(), "tag");
            }
        });

        userImageView = findViewById(R.id.userPageImage);

        user = presenter.getCurrentUser();

        // Asynchronously load the user's image
        LoadImageTask loadImageTask = new LoadImageTask(this);
        loadImageTask.execute(presenter.getCurrentUser().getImageUrl());

        TextView userName = findViewById(R.id.userPageName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userPageAlias);
        userAlias.setText(user.getAlias());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.resetSelectedUser();
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void imageLoadProgressUpdated(Integer progress) {

    }

    @Override
    public void imagesLoaded(Drawable[] drawables) {
        ImageCache.getInstance().cacheImage(user, drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }

    @Override
    public void checkFollowingSuccessful(CheckIfFollowingResponse checkIfFollowingResponse) {
        System.out.println(checkIfFollowingResponse);
        isFollowingPerson = checkIfFollowingResponse.isFollowingUser();
        if(isFollowingPerson) {
            followUnfollowButton.setText("Unfollow");
        } else {
            followUnfollowButton.setText("Follow");
        }
        followUnfollowButton.getBackground().setAlpha(255);
        followUnfollowButton.setBackgroundColor(Color.GRAY);
        followUnfollowButton.setClickable(true);
    }

    @Override
    public void checkFollowingFailure(CheckIfFollowingResponse checkIfFollowingResponse) {
        Toast.makeText(getApplicationContext(), "unable to check following, " +
                checkIfFollowingResponse.getMessage(), Toast.LENGTH_SHORT).show();
        followUnfollowButton.setText("Error");
        followUnfollowButton.setClickable(false);
    }

    @Override
    public void followUnfollowSuccessful(FollowUnfollowResponse followUnfollowResponse) {
        if(isFollowingPerson) {
            isFollowingPerson = false;
            followUnfollowButton.setText("Follow");
        } else {
            isFollowingPerson = true;
            followUnfollowButton.setText("Unfollow");
        }

        followUnfollowButton.getBackground().setAlpha(255);
        followUnfollowButton.setBackgroundColor(Color.GRAY);
        followUnfollowButton.setClickable(true);

    }

    @Override
    public void followUnfollowFailure(FollowUnfollowResponse followUnfollowResponse) {
        Toast.makeText(getApplicationContext(), "unable to follow/unfollow, " +
                followUnfollowResponse.getMessage(), Toast.LENGTH_SHORT).show();

        if(isFollowingPerson) {
            followUnfollowButton.setText("Unfollow");
        } else {
            followUnfollowButton.setText("Follow");
        }

        followUnfollowButton.getBackground().setAlpha(255);
        followUnfollowButton.setBackgroundColor(Color.GRAY);
        followUnfollowButton.setClickable(true);
    }
}
