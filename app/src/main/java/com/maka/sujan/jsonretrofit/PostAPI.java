package com.maka.sujan.jsonretrofit;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface PostAPI {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Post
    */
    @GET("/posts")
    public void getPosts(Callback<List<Post>> response);
}