package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StoryPresenterTest implements StoryPresenter.View {

    StoryPresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new StoryPresenter(this);
        user1 = new User("test", "dude", "");
        user2 = new User("test2", "dude2", "");
        status1 = new Status("hey", user1);
    }

    @Test
    void getStatusesTestSuccess() throws IOException {
        StoryRequest request = new StoryRequest(user1, 5, status1,
                UserCache.getInstance().getAuthToken());
        assertNotNull(presenter.getStatuses(request));
    }

    @Test
    void getStatusesTestFail() {
        try {
            StoryResponse response = presenter.getStatuses(null);
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }
}
