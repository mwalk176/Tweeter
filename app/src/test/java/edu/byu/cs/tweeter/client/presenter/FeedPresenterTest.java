package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FeedPresenterTest implements FeedPresenter.View{

    FeedPresenter presenter;
    User user1;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new FeedPresenter(this);
        user1 = new User("test", "dude", "");
        status1 = new Status("hey", user1);
    }

    @Test
    void getFeedStatusesSuccess() throws IOException {
        FeedRequest request = new FeedRequest(user1, 5, status1,
                UserCache.getInstance().getAuthToken());
        assertNotNull(presenter.getFeedStatuses(request));

    }

    @Test
    void getFeedStatusesFail() {
        try {
            FeedResponse result = presenter.getFeedStatuses(null);
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }
}
