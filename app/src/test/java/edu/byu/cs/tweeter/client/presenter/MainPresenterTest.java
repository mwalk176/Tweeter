package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//import edu.byu.cs.tweeter.net.ServerFacade;

public class MainPresenterTest implements MainPresenter.View{

    MainPresenter presenter;
    User user1;

    @BeforeEach
    void init() {
        presenter = new MainPresenter(this);
        user1 = new User("test", "dude", "");
    }

    @AfterEach
    void reset() {
//        ServerFacade.getInstance().setFollowersByFollowee(null);
//        ServerFacade.getInstance().setFolloweesByFollower(null);
//        ServerFacade.getInstance().setFeedsByUser(null);
//        ServerFacade.getInstance().setStoriesByUser(null);
//        ServerFacade.getInstance().setAllUsers(null);
        presenter = new MainPresenter(this);
        //LoginService.getInstance().logOutUser();
    }

    @Test
    void getCurrentUserTestSuccess() {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        assertNotNull(presenter.getCurrentUser());
    }

    @Test
    void getCurrentUserTestFail() {
            User user = presenter.getCurrentUser();
            assertNull(user);
    }

    @Test
    void logoutUserTestSuccess() throws IOException {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        presenter.logOutUser();
        assertNull(presenter.getCurrentUser());
    }

    @Test
    void logoutUserTestFail() throws IOException {
        presenter.logOutUser();
        assertEquals(presenter.getCurrentUser(), null);
    }

    @Test
    void searchForUserTestSuccess() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        //ServerFacade.getInstance().setAllUsers(users);
        //assertEquals(presenter.searchForUser(user1.getAlias()), user1);
    }

    @Test
    void searchForUserTestFail() {
        try {
            //presenter.searchForUser(user1.getAlias());
            fail();
        } catch(Exception e){
            System.out.println("it worked!");
        }
        assertTrue(true);
    }
}
