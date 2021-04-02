package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class AuthTokenDAO {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private static DynamoDB dynamoDB = new DynamoDB(client);

    private static Table table = dynamoDB.getTable("TweeterAuthTokens");


    public String addAuthToken(String associatedUser) {

        //add authToken to database
        UUID authToken = UUID.randomUUID();

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(timeZone);
        String timestamp = dateFormat.format(new Date());

        try {
            System.out.println("Adding authToken...");
            PutItemOutcome outcome = table
                    .putItem(new Item()
                            .withPrimaryKey("authToken", authToken.toString())
                            .withString("associatedUser", associatedUser)
                            .withString("timestamp", timestamp)
                    );

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return null;

        }

        return authToken.toString();
    }

    public boolean validateToken(String authToken) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(timeZone);
        String newTimestamp = dateFormat.format(new Date());

        try {
            Item item = table.getItem("authToken", authToken);
            if(item == null) {
                return false;
            }
            String oldTimestamp = item.getString("timestamp");
            String associatedUser = item.getString("associatedUser");
            //convert both timestamps into ints and compare them
            System.out.println("oldTimestamp: " + oldTimestamp);
            System.out.println("newTimestamp: " + newTimestamp);
            int oldHour = Integer.parseInt(oldTimestamp.substring(11,13));
            int oldMinute = Integer.parseInt(oldTimestamp.substring(14,16)) + (oldHour * 60);
            int newHour = Integer.parseInt(newTimestamp.substring(11,13));
            int newMinute = Integer.parseInt(newTimestamp.substring(14,16)) + (newHour * 60);
            System.out.println("Old Minute: " + oldMinute);
            System.out.println("New Minute: " + newMinute);
            if(Math.abs(newMinute - oldMinute) > 2) {
                return false;
            }

            table.deleteItem("authToken", authToken);

            PutItemOutcome outcome = table
                    .putItem(new Item()
                            .withPrimaryKey("authToken", authToken.toString())
                            .withString("associatedUser", associatedUser)
                            .withString("timestamp", newTimestamp)
                    );

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean invalidateToken(String authToken, String associatedUser) {
        try {
            Item item = table.getItem("authToken", authToken);
            if(item == null) {
                return true;
            }

            table.deleteItem("authToken", authToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
