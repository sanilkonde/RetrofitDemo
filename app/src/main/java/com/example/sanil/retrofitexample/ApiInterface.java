package com.example.sanil.retrofitexample;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by sanil on 23-08-2017.
 */

public interface ApiInterface {


    @GET("GetList")
    Call<String> getList();

    @FormUrlEncoded
    @POST("burgundy_authnew.aspx")
    Call<ModelArrayPOST>login(@Field("data") String data);


//    @GET("octocat")
//    Observable<ModelGET> savePost();
//
//    @GET("posts")
//    Observable<List<ModelGET>> savePost();
//
//    @FormUrlEncoded
//    @POST("burgundy_authnew.aspx")
////    Observable<ModelArrayPOST> login(@Header("Cookie") String cookie, @Field("data") String data);
//    Observable<ModelArrayPOST> login(@Field("data") String data);
//
//    @FormUrlEncoded
//    @POST("ret_register")
//    Observable<String> verifyUser(@Field("data") String data);
//
//    @FormUrlEncoded
//    @POST("ret_register")
//    Observable<ModelPostObject> verifyUserForModel(@Field("data") String data);
}
