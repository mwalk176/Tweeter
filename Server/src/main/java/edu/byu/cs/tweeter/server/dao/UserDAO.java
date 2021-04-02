package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class UserDAO {

    public LoginResponse loginUser(LoginRequest request) {


        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("TweeterUsers");

        //password hashing
        byte[] hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            hashedPassword = md.digest(request.getPassword().getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new LoginResponse(false, "Error occurred while hashing password");
        }

        //try to get user from database
        Item item;
        try {
            item = table.getItem("alias", request.getUsername());
            if(item == null) {
                return new LoginResponse(false, "Error: User does not exist. Please register instead.");
            }

            if(!item.get("password").equals(Arrays.toString(hashedPassword))) {
                return new LoginResponse(false, "Error: Incorrect Password");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return new LoginResponse(false, "Error occurred while accessing database");
        }

        User user = new User(item.get("firstName").toString(),
                item.get("lastName").toString(), item.get("alias").toString(),
                item.get("imageUrl").toString());
        return new LoginResponse("placeHolderToken", user);
    }

    public RegisterResponse registerUser(RegisterRequest request) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("TweeterUsers");

        //password hashing
        byte[] hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            hashedPassword = md.digest(request.getPassword().getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new RegisterResponse(false, "Error occurred while hashing password");
        }

        //try to add user to database
        try {
            Item item = table.getItem("alias", request.getAlias());
            if(item != null) {
                return new RegisterResponse(false, "Error: User already exists. Please Log in instead.");
            }

            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(new Item()
                            .withPrimaryKey("alias", request.getAlias())
                            .withString("firstName", request.getFirstName())
                            .withString("lastName", request.getLastName())
                            .withString("password", Arrays.toString(hashedPassword))
                            .withString("imageUrl", "https://mwalktest.s3.us-east-2.amazonaws.com/donald_duck.png")
                    );

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return new RegisterResponse(false, "Error occurred while accessing database");
        }

        User user = new User(request.getFirstName(), request.getLastName(), request.getAlias(),
                "https://mwalktest.s3.us-east-2.amazonaws.com/donald_duck.png");
        return new RegisterResponse("placeHolderToken", user);
    }

}
