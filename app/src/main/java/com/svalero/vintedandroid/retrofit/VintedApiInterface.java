package com.svalero.vintedandroid.retrofit;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface VintedApiInterface {

    @POST("Controller")
    Call<Product> addProduct(@QueryMap HashMap<String, String> params);

    @GET("Controller")
    Call<List<Product>> getProducts(@QueryMap HashMap<String, String> params);

    @GET("Controller")
    Call<List<Product>> getProduct(@QueryMap HashMap<String, String> params);

    @GET("Controller")
    Call<List<User>> getUsers(@QueryMap HashMap<String, String> params);

    @GET("Controller")
    Call<List<User>> getUser(@QueryMap HashMap<String, String> params);
}
