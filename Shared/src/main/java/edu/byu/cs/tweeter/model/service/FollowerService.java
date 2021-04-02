package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

import java.io.IOException;

/**
 * Contains the business logic for getting the users a user is following.
 */
public interface FollowerService {

//    /**
//     * The singleton instance.
//     */
//    private static FollowerService instance;
//
//    //private final ServerFacade serverFacade;
//
//    /**
//     * Return the singleton instance of this class.
//     *
//     * @return the instance.
//     */
//    public static FollowerService getInstance() {
//        if(instance == null) {
//            instance = new FollowerService();
//        }
//
//        return instance;
//    }
//
//    /**
//     * A private constructor created to ensure that this class is a singleton (i.e. that it
//     * cannot be instantiated by external classes).
//     */
//    private FollowerService() {
//        //serverFacade = new ServerFacade();
//    }

    FollowerResponse getFollowers(FollowerRequest request) throws IOException;
    /*{
        return ServerFacade.getInstance().getFollowers(request);
    }*/
}
