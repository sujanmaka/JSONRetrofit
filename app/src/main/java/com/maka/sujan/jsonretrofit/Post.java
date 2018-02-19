package com.maka.sujan.jsonretrofit;


public class Post {

    //Variables that are in our json
    private String  userId;
    private String id;
    private String name;
    private String body;



   /* public Post(int userId, int id, String name, String body) {
    }*/

    //Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}