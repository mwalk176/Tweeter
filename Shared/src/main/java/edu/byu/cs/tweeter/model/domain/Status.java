package edu.byu.cs.tweeter.model.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Status implements Comparable<Status>{

    private String message;
    private String timestamp;
    private User associatedUser;
    private User mentionedUser;
    private String URL;

    public Status() {
    }

    public Status(String message, User user) {
        this.message = message;
        this.associatedUser = user;
        Date currentTime = new Date();
        timestamp = new Timestamp(currentTime.getTime()).toString();
        mentionedUser = null;
        URL = null;
        parseStatus();
    }

    public Status(String message, String timestamp, User associatedUser) {
        this.message = message;
        this.timestamp = timestamp;
        this.associatedUser = associatedUser;
        mentionedUser = null;
        this.URL = null;
    }

    private void parseStatus() {
        //todo go through a status and find all the mentioned users/urls
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public User getMentionedUser() {
        return mentionedUser;
    }

    public String getURL() {
        return URL;
    }

    @Override
    public int compareTo(Status s) {
        return this.timestamp.compareTo(s.timestamp);
    }

    @Override
    public String toString() {
        return "Status{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", associatedUser=" + associatedUser +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public void setMentionedUser(User mentionedUser) {
        this.mentionedUser = mentionedUser;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
