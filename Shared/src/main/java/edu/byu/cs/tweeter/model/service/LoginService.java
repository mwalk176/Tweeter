package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import java.io.IOException;

/**
 * Contains the business logic for login and sign up.
 */
public interface LoginService {

//    /**
//     * The singleton instance.
//     */
//    private static LoginService instance;
//
//    /**
//     * The logged in user.
//     */
//    private User currentUser;
//
//    /**
//     * Return the singleton instance of this class.
//     *
//     * @return the instance.
//     */
//    public static LoginService getInstance() {
//        if(instance == null) {
//            instance = new LoginService();
//        }
//
//        return instance;
//    }
//
//    /**
//     * A private constructor created to ensure that this class is a singleton (i.e. that it
//     * cannot be instantiated by external classes).
//     */
//    private LoginService() {
//        // TODO: Remove when the actual login functionality exists.
//        /*currentUser = new User("Test", "User",
//                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        setCurrentUser(currentUser);*/
//        currentUser = null;
//    }
//
//    /**
//     * Returns the currently logged in user.
//     *
//     * @return the user.
//     */
//    public User getCurrentUser() {
//        return currentUser;
//    }

    /*void setCurrentUser(User currentUser); {
        this.currentUser = currentUser;
        UserCache.getInstance().setCurrentlyLoggedInUser(currentUser);
        UserCache.getInstance().setCurrentlySelectedUser(currentUser);
    }*/

    LoginResponse loginUser(LoginRequest request) throws IOException; /*{
        ServerFacade serverFacade = new ServerFacade();
        User user = serverFacade.loginUser(username, password);
        setCurrentUser(user);


        *//*User user = UserCache.getInstance().findUser(username);
        if(user.getPassword() == password) {
            setCurrentUser(user);
        } else {
            //TODO Add observer stuff here becaues they failed to log in
            System.out.println("password incorrect!");
        }*/
    //}

    /*RegisterResponse registerUser(RegisterRequest request); {
        //ServerFacade serverFacade = new ServerFacade();
        User user = ServerFacade.getInstance().registerUser(firstName, lastName, alias, password);
        setCurrentUser(user);
    }*/

    /* {
        setCurrentUser(null);
    }*/
}
