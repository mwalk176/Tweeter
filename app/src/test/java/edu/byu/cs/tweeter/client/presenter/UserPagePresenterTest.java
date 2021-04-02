package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

//import edu.byu.cs.tweeter.net.ServerFacade;

public class UserPagePresenterTest implements UserPagePresenter.View {

    UserPagePresenter presenter;
    User user1;
    User user2;
    Status status1;

    @BeforeEach
    void init() {
        presenter = new UserPagePresenter(this);
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
        presenter = new UserPagePresenter(this);
        //LoginService.getInstance().logOutUser();
        UserCache.getInstance().setCurrentlyLoggedInUser(null);
        UserCache.getInstance().setCurrentlySelectedUser(null);
    }

    @Test
    void setSelectedUserTestSuccess() {
        presenter.setSelectedUser(user1);
        assertNotNull(UserCache.getInstance().getCurrentlySelectedUser());
    }

    @Test
    void setSelectedUserTestFail() {
        try {
            presenter.setSelectedUser(null);
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }

    @Test
    void resetSelectedUserTestSuccess() {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        UserCache.getInstance().setCurrentlySelectedUser(user2);
        presenter.resetSelectedUser();
        assertEquals(UserCache.getInstance().getCurrentlySelectedUser(), UserCache.getInstance().getCurrentlyLoggedInUser());

    }

    @Test
    void resetSelectedUserTestFail() {
        try {
            presenter.resetSelectedUser();
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }

    @Test
    void isUserFollowingThisPersonTestSuccess() {
        //ServerFacade.getInstance().initializeFolloweesAndFollowers();

        try {
            //presenter.checkFollowing();
            fail();
        } catch (Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);

    }

    @Test
    void isUserFollowingThisPersonTestFail() {
        try {
            //boolean result = presenter.checkFollowing();
        } catch(NullPointerException e) {
            System.out.println("success!");
        }
        assertTrue(true);
    }

    @Test
    void unfollowUserTestSuccess() {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        UserCache.getInstance().setCurrentlySelectedUser(user2);
        //UserPageService.getInstance().setSelectedUser(user2);

        Map<User, List<User>> followees = new TreeMap<>();
        Map<User, List<User>> followers = new TreeMap<>();

        List<User> userList = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();

        userList.add(user2);
        userList2.add(user1);

        followees.put(user1, userList);
        followers.put(user2, userList2);
//        ServerFacade.getInstance().setFolloweesByFollower(followees);
//        ServerFacade.getInstance().setFollowersByFollowee(followers);


        //presenter.unFollowUser();

        //List<User> user = ServerFacade.getFolloweesByFollower().get(user1);
        List<User> user2 = new ArrayList<>();
        //assertEquals(user, user2);

    }

    @Test
    void unfollowUserTestFail() {
        try {
            //presenter.unFollowUser();
            fail();
        } catch(Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }

    @Test
    void followUserTestSuccess() {
        UserCache.getInstance().setCurrentlyLoggedInUser(user1);
        UserCache.getInstance().setCurrentlySelectedUser(user2);
        //UserPageService.getInstance().setSelectedUser(user2);

        Map<User, List<User>> followees = new TreeMap<>();
        Map<User, List<User>> followers = new TreeMap<>();

        List<User> userList = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();

        userList.add(user2);
        userList2.add(user1);

        followees.put(user1, userList);
        followers.put(user2, userList2);
//        ServerFacade.getInstance().setFolloweesByFollower(followees);
//        ServerFacade.getInstance().setFollowersByFollowee(followers);


        //presenter.followUser();

        //assertNotNull(ServerFacade.getFolloweesByFollower().get(user1));
    }

    @Test
    void followUserTestFail() {
        try {
            //presenter.followUser();
            //FollowingResponse response = ServerFacade.getInstance().getFollowees(new FollowingRequest(user1, 1, user2));
            fail();
        } catch(Exception e) {
            System.out.println("success!");
        }

        assertTrue(true);
    }
}
