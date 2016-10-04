package com.maka.sujan.jsonretrofit;


public class Post {

    //Variables that are in our json
    private int userId;
    private int id;
    private String title;
    private String body;



   /* public Post(int userId, int id, String title, String body) {
    }*/

    //Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int bookId) {
        this.userId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}