package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;

import java.io.IOException;

public interface StatusService {

//    private static StatusService instance;
//    //private final ServerFacade serverFacade;
//
//    public static StatusService getInstance() {
//        if(instance == null) {
//            instance = new StatusService();
//        }
//        return instance;
//    }
//
//    public StatusService() {
//        //serverFacade = new ServerFacade();
//    }

    PostStatusResponse postStatus(PostStatusRequest request) throws IOException; /* {
        Status status = new Status(message, associatedUser);
        ServerFacade.getInstance().addToStory(status);
    }*/

}
