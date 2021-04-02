package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

//import edu.byu.cs.tweeter.net.ServerFacade;

public class RegisterPresenterTest implements RegisterPresenter.View{

    RegisterPresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new RegisterPresenter(this);
        user1 = new User("test", "dude", "");
        user2 = new User("test2", "dude2", "");
        status1 = new Status("hey", user1);
    }

    @AfterEach
    void reset() {
//        ServerFacade.getInstance().setFollowersByFollowee(null);
//        ServerFacade.getInstance().setFolloweesByFollower(null);
//        ServerFacade.getInstance().setFeedsByUser(null);
//        ServerFacade.getInstance().setStoriesByUser(null);
//        ServerFacade.getInstance().setAllUsers(null);
        presenter = new RegisterPresenter(this);
        //LoginService.getInstance().logOutUser();
        UserCache.getInstance().setCurrentlyLoggedInUser(null);
        UserCache.getInstance().setCurrentlySelectedUser(null);
    }

    @Test
    void registerUserTestSuccess() {
//        presenter.registerUser("first", "last", "testboi", "tsetPassword");
//        assertNotNull(UserCache.getInstance().getCurrentlyLoggedInUser());
    }

    @Test
    void registerUserTestFail() {
        User user = UserCache.getInstance().getCurrentlyLoggedInUser();
        assertNull(user);
    }
}
