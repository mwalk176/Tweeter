package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.io.IOException;

public interface StoryService {

//    private static StoryService instance;
//    //private final ServerFacade serverFacade;
//
//    public static StoryService getInstance() {
//        if(instance == null) {
//            instance = new StoryService();
//        }
//        return instance;
//    }
//
//    public StoryService() {
//        //s/erverFacade = new ServerFacade();
//    }

    public StoryResponse getStatuses(StoryRequest storyRequest) throws IOException; /*{
        return ServerFacade.getInstance().getStatuses(storyRequest);
    }*/
}
