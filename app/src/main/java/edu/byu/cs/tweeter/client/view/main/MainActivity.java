package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;
import edu.byu.cs.tweeter.client.presenter.UserPagePresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoadImageTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements LoadImageTask.LoadImageObserver, LogoutTask.LogoutObserver, MainPresenter.View , UserPagePresenter.View{

    final LogoutTask.LogoutObserver observer = this;

    private MainPresenter presenter;
    private User user;
    private ImageView userImageView, searchButton;
    private EditText searchField;
    User foundUser;

    UserPagePresenter pagePresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_login);

        presenter = new MainPresenter(this);
        pagePresenter = new UserPagePresenter(this);

        if(presenter.getCurrentUser() == null) {
            //go to login activity
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PostStatusDialog postStatusDialog = new PostStatusDialog();
                    postStatusDialog.show(getSupportFragmentManager(), "tag");
                }
            });

            userImageView = findViewById(R.id.userImage);
            searchButton = findViewById(R.id.searchButton);
            searchButton.setVisibility(View.INVISIBLE);
            searchField = findViewById(R.id.searchField);
            searchField.setVisibility(View.INVISIBLE);

            user = presenter.getCurrentUser();

            // Asynchronously load the user's image
            LoadImageTask loadImageTask = new LoadImageTask(this);
            loadImageTask.execute(presenter.getCurrentUser().getImageUrl());

            userImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(MainActivity.this, userImageView);
                    popupMenu.getMenuInflater().inflate(R.menu.user_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getTitle().toString().equals("My Profile")) {
                                pagePresenter.setSelectedUser(presenter.getCurrentUser());

                                Intent intent = new Intent(getApplicationContext(), UserPageActivity.class);
                                startActivity(intent);
                            } else if(item.getTitle().toString().equals("Log out")) {
                                Toast.makeText(getApplicationContext(), "Signing out....", Toast.LENGTH_SHORT).show();
                                LogoutTask logoutTask = new LogoutTask(observer);
                                logoutTask.execute();

                            }

                            return false;
                        }
                    });

                    popupMenu.show();
                }
            });

            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userAlias = searchField.getText().toString();
                    if(foundUser == null) {
                        Toast.makeText(getApplicationContext(), "Invalid User!", Toast.LENGTH_SHORT).show();
                    } else {
                        pagePresenter.setSelectedUser(foundUser);

                        Intent intent = new Intent(getApplicationContext(), UserPageActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }


    }

    @Override
    public void imageLoadProgressUpdated(Integer progress) {
        // We're just loading one image. No need to indicate progress.
    }

    /**
     * A callback that indicates that the image for the user being displayed on this activity has
     * been loaded.
     *
     * @param drawables the drawables (there will only be one).
     */
    @Override
    public void imagesLoaded(Drawable[] drawables) {
        ImageCache.getInstance().cacheImage(user, drawables[0]);

        if(drawables[0] != null) {
            userImageView.setImageDrawable(drawables[0]);
        }
    }

    @Override
    public void logoutSuccessful(LogoutResponse response) {
        Toast.makeText(getApplicationContext(), "Sign out successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void logoutFailure(LogoutResponse response) {
        Toast.makeText(getApplicationContext(), "Sign out error, " + response.getMessage(), Toast.LENGTH_SHORT).show();
    }
}