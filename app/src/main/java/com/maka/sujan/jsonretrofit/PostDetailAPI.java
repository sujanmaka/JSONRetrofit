package com.maka.sujan.jsonretrofit;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;



public interface PostDetailAPI {


    @GET("/posts/{id}/comments")
    public void getPosts1(@Path("id") String  postId, Callback<List<PostDetail>> response);
}
