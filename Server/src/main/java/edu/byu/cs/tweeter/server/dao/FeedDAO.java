package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedDAO {

    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
    private static Table table = dynamoDB.getTable("TweeterFeed");

    public FeedResponse getFeedStatuses(FeedRequest request) {
        try {
            User associatedUser = request.getAssociatedUser();
            int limit = request.getLimit();
            Status lastStatus = request.getLastStatus();


            Map<String, String> attrNames = new HashMap<String, String>();
            attrNames.put("#associatedUser", "associatedUser");

            Map<String, AttributeValue> attrValues = new HashMap<>();
            attrValues.put(":userAlias", new AttributeValue().withS(associatedUser.getAlias()));

            QueryRequest queryRequest = new QueryRequest()
                    .withTableName("TweeterFeed")
                    .withIndexName("associatedUser-index")
                    .withKeyConditionExpression("#associatedUser = :userAlias")
                    .withExpressionAttributeNames(attrNames)
                    .withExpressionAttributeValues(attrValues)
                    .withLimit(limit);

            if (lastStatus != null) {
                Map<String, AttributeValue> lastKey = new HashMap<>();
                lastKey.put("associatedUser", new AttributeValue().withS(associatedUser.getAlias()));
                lastKey.put("lastStatusTimestamp", new AttributeValue().withS(lastStatus.getTimestamp()));


                queryRequest = queryRequest.withExclusiveStartKey(lastKey);
            }

            QueryResult queryResult = amazonDynamoDB.query(queryRequest);
            List<Map<String, AttributeValue>> items = queryResult.getItems();

            List<Status> statuses = new ArrayList<>();

            if (items != null) {
                for (Map<String, AttributeValue> item : items){
                    String message = item.get("message").getS();
                    String timestamp = item.get("timestamp").getS();

                    String firstName = item.get("originalPoster_firstName").getS();
                    String lastName = item.get("originalPoster_lastName").getS();
                    String alias = item.get("originalPoster_handle").getS();
                    String imageUrl = item.get("originalPoster_imageUrl").getS();
                    User originalPoster = new User(firstName, lastName, alias, imageUrl);

                    Status status = new Status(message, timestamp, originalPoster);
                    statuses.add(status);
                }
            }
            boolean hasMorePages = false;
            Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
            if (lastKey != null) {
                hasMorePages = true;
            }
            FeedResponse response = new FeedResponse(statuses, hasMorePages);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new FeedResponse("error occurred while getting feed");
        }
    }
}
