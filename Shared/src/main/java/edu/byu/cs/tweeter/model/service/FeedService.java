package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.io.IOException;

public interface FeedService {

//    private static FeedService instance;
//    //private final ServerFacade serverFacade;
//
//    public static FeedService getInstance() {
//        if(instance == null) {
//            instance = new FeedService();
//        }
//        return instance;
//    }
//
//    public FeedService() {
//        //s/erverFacade = new ServerFacade();
//    }

    FeedResponse getFeedStatuses(FeedRequest feedRequest) throws IOException; /*{
        return ServerFacade.getInstance().getFeedStatuses(feedRequest);
    }*/
}
