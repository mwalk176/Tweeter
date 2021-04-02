package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import java.io.IOException;

/**
 * Contains the business logic for getting the users a user is following.
 */
public interface FollowingService {

//    /**
//     * The singleton instance.
//     */
//    private static FollowingService instance;
//
//    //private final ServerFacade serverFacade;
//
//    /**
//     * Return the singleton instance of this class.
//     *
//     * @return the instance.
//     */
//    public static FollowingService getInstance() {
//        if(instance == null) {
//            instance = new FollowingService();
//        }
//
//        return instance;
//    }
//
//    /**
//     * A private constructor created to ensure that this class is a singleton (i.e. that it
//     * cannot be instantiated by external classes).
//     */
//    private FollowingService() {
//        //serverFacade = new ServerFacade();
//    }

    FollowingResponse getFollowees(FollowingRequest request) throws IOException; /*{
        return ServerFacade.getInstance().getFollowees(request);
    }*/
}
