package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class StoryDAO {

    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
    private static Table table = dynamoDB.getTable("TweeterStory");

    public StoryResponse getStatuses(StoryRequest request) {
        try {
            User associatedUser = request.getAssociatedUser();
            int limit = request.getLimit();
            Status lastStatus = request.getLastStatus();


            Map<String, String> attrNames = new HashMap<String, String>();
            attrNames.put("#associatedUser", "associatedUser");

            Map<String, AttributeValue> attrValues = new HashMap<>();
            attrValues.put(":userAlias", new AttributeValue().withS(associatedUser.getAlias()));

            QueryRequest queryRequest = new QueryRequest()
                    .withTableName("TweeterStory")
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
            StoryResponse response = new StoryResponse(statuses, hasMorePages);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new StoryResponse("error occurred while getting story");
        }
    }

    public PostStatusResponse postStatus(PostStatusRequest request) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(timeZone);
        String timestamp = dateFormat.format(new Date());

        try {
            System.out.println("Adding status...");
            PutItemOutcome outcome = table
                .putItem(new Item()
                .withPrimaryKey("associatedUser", request.getAssociatedUser().getAlias())
                .withString("timestamp", timestamp)
                .withString("message", request.getMessage())
                .withString("originalPoster_handle",
                        request.getAssociatedUser().getAlias())
                .withString("originalPoster_firstName",
                        request.getAssociatedUser().getFirstName())
                .withString("originalPoster_lastName",
                        request.getAssociatedUser().getLastName())
                .withString("originalPoster_imageUrl",
                        request.getAssociatedUser().getImageUrl())
                );

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return new PostStatusResponse(false,
                    "error occurred while adding to story database");

        }
        return new PostStatusResponse(true);
    }
}
