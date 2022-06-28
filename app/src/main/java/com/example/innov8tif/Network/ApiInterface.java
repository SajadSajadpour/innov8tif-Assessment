package com.example.innov8tif.Network;

import com.example.innov8tif.Model.CommentsData;
import com.example.innov8tif.Model.PostData;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit2.Call;


public interface ApiInterface {

    // API's endpoints
    @GET("/posts")
    void getPostList(retrofit.Callback<List<PostData>> callback);


    // API's endpoints
    @GET("/post/{post_id}/comments")
    void getComments(@Path("post_id") int id_, Callback<List<CommentsData>> listCallback);
}
