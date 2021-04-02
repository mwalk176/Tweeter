package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FollowerPresenterTest implements FollowerPresenter.View {

    FollowerPresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new FollowerPresenter(this);
        user1 = new User("test", "dude", "");
        user2 = new User("test2", "dude2", "");
        status1 = new Status("hey", user1);
    }

    @Test
    void getFollowersTestSuccess() throws IOException {
        FollowerRequest request = new FollowerRequest(user1, 5, user2,
                UserCache.getInstance().getAuthToken());
        assertNotNull(presenter.getFollowers(request));
    }

    @Test
    void getFollowersTestFail() {
        try {
            presenter.getFollowers(null);
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);

    }
}
