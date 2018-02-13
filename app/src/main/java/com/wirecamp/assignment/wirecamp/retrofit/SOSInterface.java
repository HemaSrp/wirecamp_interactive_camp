package com.wirecamp.assignment.wirecamp.retrofit;

import com.wirecamp.assignment.wirecamp.model.Response;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * This interface is used to send the parameter and returns call back for the response.
 */

public interface SOSInterface {

    @GET("/v1/connections?")
    Call<Response> getPhotos(@Query("from") String from, @Query("to") String to);



}
