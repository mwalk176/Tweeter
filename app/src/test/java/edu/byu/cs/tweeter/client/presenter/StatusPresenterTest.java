package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//import edu.byu.cs.tweeter.net.ServerFacade;

public class StatusPresenterTest implements StatusPresenter.View {

    StatusPresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new StatusPresenter(this);
        user1 = new User("test", "dude", "");
        user2 = new User("test2", "dude2", "");
        status1 = new Status("hey", user1);
    }

    @Test
    void postStatusTestSuccess() {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        Map<User, List<User>> fbf = new TreeMap<>();
        List<User> follower = new ArrayList<>();
        follower.add(user2);
        fbf.put(user1, follower);
        //ServerFacade.getInstance().setFollowersByFollowee(fbf);
        //presenter.postStatus("hey this is a test");
        //assertNotNull(ServerFacade.getStoriesByUser().get(user1));
    }

    @Test
    void postStatusTestFail() {
        try {
            presenter.postStatus(null);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
            assertTrue(true);
    }
}
