package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import edu.byu.cs.tweeter.net.ServerFacade;

public class LoginPresenterTest implements LoginPresenter.View{

    LoginPresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new LoginPresenter(this);
        user1 = new User("test", "dude", "");
        user2 = new User("test2", "dude2", "");
        status1 = new Status("hey", user1);
        UserCache.getInstance().setCurrentlyLoggedInUser(null);
        UserCache.getInstance().setCurrentlySelectedUser(null);
    }

    @AfterEach
    void reset() {
//        ServerFacade.getInstance().setFollowersByFollowee(null);
//        ServerFacade.getInstance().setFolloweesByFollower(null);
//        ServerFacade.getInstance().setFeedsByUser(null);
//        ServerFacade.getInstance().setStoriesByUser(null);
//        ServerFacade.getInstance().setAllUsers(null);
        //LoginService.getInstance().logOutUser();
        UserCache.getInstance().setCurrentlyLoggedInUser(null);
        UserCache.getInstance().setCurrentlySelectedUser(null);
    }

    @Test
    void loginUserTestSuccess() {
//        presenter.loginUser(user1.getAlias(), "testPassword");
//        assertNotNull(UserCache.getInstance().getCurrentlyLoggedInUser());

    }

    @Test
    void loginUserTestFail() {
//        presenter.loginUser(null, null);
//        assertNotEquals(UserCache.getInstance().getCurrentlyLoggedInUser(), user1);
    }
}
