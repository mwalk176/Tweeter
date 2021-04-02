package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckIfFollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUnfollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.CheckIfFollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUnfollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowDAO {

    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
    private static Table table = dynamoDB.getTable("TweeterFollows");


    public FollowerResponse getFollowers(FollowerRequest request) {
        try {
            User followee = request.getFollowee();
            int limit = request.getLimit();
            User lastFollower = request.getLastFollower();


            Map<String, String> attrNames = new HashMap<String, String>();
            attrNames.put("#followee", "followee_handle");

            Map<String, AttributeValue> attrValues = new HashMap<>();
            attrValues.put(":followerAlias", new AttributeValue().withS(followee.getAlias()));

            QueryRequest queryRequest = new QueryRequest()
                    .withTableName("TweeterFollows")
                    .withIndexName("followee-index")
                    .withKeyConditionExpression("#followee = :followerAlias")
                    .withExpressionAttributeNames(attrNames)
                    .withExpressionAttributeValues(attrValues)
                    .withLimit(limit);

            if (lastFollower != null) {
                Map<String, AttributeValue> lastKey = new HashMap<>();
                lastKey.put("followee_handle", new AttributeValue().withS(followee.getAlias()));
                lastKey.put("follower_handle", new AttributeValue().withS(lastFollower.getAlias()));

                queryRequest = queryRequest.withExclusiveStartKey(lastKey);
            }

            QueryResult queryResult = amazonDynamoDB.query(queryRequest);
            List<Map<String, AttributeValue>> items = queryResult.getItems();

            List<User> followers = new ArrayList<>();

            if (items != null) {
                for (Map<String, AttributeValue> item : items){
                    String firstName = item.get("follower_firstName").getS();
                    String lastName = item.get("follower_lastName").getS();
                    String alias = item.get("follower_handle").getS();
                    String imageUrl = item.get("follower_imageUrl").getS();
                    User follower = new User(firstName, lastName, alias, imageUrl);
                    followers.add(follower);
                }
            }
            boolean hasMorePages = false;
            Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
            if (lastKey != null) {
                hasMorePages = true;
            }
            FollowerResponse response = new FollowerResponse(followers, hasMorePages);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new FollowerResponse("error occurred while getting followers");
        }


    }

    public FollowingResponse getFollowees(FollowingRequest request) {
        System.out.println("Starting getFollowees");
        if(request == null) return new FollowingResponse("error, request is null");
        try {
            User follower = request.getFollower();
            int limit = request.getLimit();
            User lastFollowee = request.getLastFollowee();

            System.out.println(request);


            Map<String, String> attrNames = new HashMap<String, String>();
            attrNames.put("#follower", "follower_handle");

            Map<String, AttributeValue> attrValues = new HashMap<>();
            attrValues.put(":followeeAlias", new AttributeValue().withS(follower.getAlias()));

            QueryRequest queryRequest = new QueryRequest()
                    .withTableName("TweeterFollows")
                    .withIndexName("follower-index")
                    .withKeyConditionExpression("#follower = :followeeAlias")
                    .withExpressionAttributeNames(attrNames)
                    .withExpressionAttributeValues(attrValues)
                    .withLimit(limit);

            if (lastFollowee != null) {
                Map<String, AttributeValue> lastKey = new HashMap<>();
                lastKey.put("follower_handle", new AttributeValue().withS(follower.getAlias()));
                lastKey.put("followee_handle", new AttributeValue().withS(lastFollowee.getAlias()));

                queryRequest = queryRequest.withExclusiveStartKey(lastKey);
            }
            System.out.println(queryRequest.toString());
            QueryResult queryResult = amazonDynamoDB.query(queryRequest);
            System.out.println(queryResult.toString());
            List<Map<String, AttributeValue>> items = queryResult.getItems();


            List<User> followees = new ArrayList<>();

            if (items != null) {
                System.out.println(items.toString());
                for (Map<String, AttributeValue> item : items){
                    String firstName = item.get("followee_firstName").getS();
                    String lastName = item.get("followee_lastName").getS();
                    String alias = item.get("followee_handle").getS();
                    String imageUrl = item.get("followee_imageUrl").getS();
                    User followee = new User(firstName, lastName, alias, imageUrl);
                    followees.add(followee);
                }
            }
            boolean hasMorePages = false;
            Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
            if (lastKey != null) {
                hasMorePages = true;
            }

            FollowingResponse response = new FollowingResponse(followees, hasMorePages);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new FollowingResponse("Error occurred while getting followees");
        }

    }

    public FollowUnfollowResponse followUnfollowUser(FollowUnfollowRequest request) {
        boolean isAlreadyFollowing = false;
        if(request.getFollowing() != null) {
            if(request.getFollowing().equals("true")) isAlreadyFollowing = true;
        }

        System.out.println("starting followUnfollowUser function:");
        System.out.println("Request is " + request.toString());

        if(request.getLoggedInUser().getAlias().equals(request.getSelectedUser().getAlias())) {
            return new FollowUnfollowResponse(false,
                    "You can't follow/unfollow yourself!");
        }

        if(isAlreadyFollowing) {
            //unfollow
            System.out.println("attempting to unfollow user");
            try {
                Item item = table.getItem("follower_handle", request.getLoggedInUser().getAlias(),
                        "followee_handle", request.getSelectedUser().getAlias());
                if(item == null) {
                    PutItemOutcome outcome = table
                            .putItem(new Item()
                                    .withPrimaryKey("follower_handle", request.getLoggedInUser().getAlias())
                                    .withString("followee_handle", request.getSelectedUser().getAlias())
                                    .withString("follower_firstName", request.getLoggedInUser().getFirstName())
                                    .withString("follower_lastName", request.getLoggedInUser().getLastName())
                                    .withString("follower_imageUrl", request.getLoggedInUser().getImageUrl())
                                    .withString("followee_firstName", request.getSelectedUser().getFirstName())
                                    .withString("followee_lastName", request.getSelectedUser().getLastName())
                                    .withString("followee_imageUrl", request.getSelectedUser().getImageUrl())
                            );
                    System.out.println(outcome.getPutItemResult().toString());
                    //return new FollowUnfollowResponse(true);
                }

                DeleteItemOutcome outcome = table.deleteItem("follower_handle", request.getLoggedInUser().getAlias(),
                        "followee_handle", request.getSelectedUser().getAlias());
                System.out.println(outcome.getDeleteItemResult().toString());
            } catch (Exception e) {
                e.printStackTrace();
                return new FollowUnfollowResponse(false,
                        "error occured while unfollowing user");
            }
        } else {
            //follow
            System.out.println("attempting to follow user");
            try {
                Item item = table.getItem("follower_handle", request.getLoggedInUser().getAlias(),
                        "followee_handle", request.getSelectedUser().getAlias());
                if(item != null) {
                    DeleteItemOutcome outcome = table.deleteItem("follower_handle", request.getLoggedInUser().getAlias(),
                            "followee_handle", request.getSelectedUser().getAlias());
                    System.out.println(outcome.getDeleteItemResult().toString());
                    //return new FollowUnfollowResponse(true);
                }

                PutItemOutcome outcome = table
                        .putItem(new Item()
                                .withPrimaryKey("follower_handle", request.getLoggedInUser().getAlias())
                                .withString("followee_handle", request.getSelectedUser().getAlias())
                                .withString("follower_firstName", request.getLoggedInUser().getFirstName())
                                .withString("follower_lastName", request.getLoggedInUser().getLastName())
                                .withString("follower_imageUrl", request.getLoggedInUser().getImageUrl())
                                .withString("followee_firstName", request.getSelectedUser().getFirstName())
                                .withString("followee_lastName", request.getSelectedUser().getLastName())
                                .withString("followee_imageUrl", request.getSelectedUser().getImageUrl())
                        );
                System.out.println(outcome.getPutItemResult().toString());
            } catch (Exception e) {
                e.printStackTrace();
                return new FollowUnfollowResponse(false,
                        "error occured while following user");
            }
        }
        return new FollowUnfollowResponse(true);
    }

    public CheckIfFollowingResponse checkIfFollowing(CheckIfFollowingRequest request) {
        System.out.println("Checking if " + request.getLoggedInUser().getAlias() +
                " is following " + request.getSelectedUser().getAlias());
        try {
            Item item = table.getItem("follower_handle", request.getLoggedInUser().getAlias(),
                    "followee_handle", request.getSelectedUser().getAlias());
            if(item != null) {
                System.out.println(item.toString());
                System.out.println("YES user is following person");
                return new CheckIfFollowingResponse(true, true);
            } else {
                System.out.println("NO user is not following person");
                return new CheckIfFollowingResponse(true, false);
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
            e.printStackTrace();
            return new CheckIfFollowingResponse(false,
                    "error occurred while checking user's following");
        }
    }

}


