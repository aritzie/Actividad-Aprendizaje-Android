package com.svalero.vintedandroid.retrofit;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit;
    private Context context;

    private static final String URL = "http://192.168.1.130:8084/Vinted/";

    public ApiClient(Context context){
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<Product> addProduct(HashMap<String, String> params){
        VintedApiInterface service = retrofit.create(VintedApiInterface.class);
        return service.addProduct(params);
    }

    public Call<List<Product>> getProducts(HashMap<String, String> params){
        VintedApiInterface service = retrofit.create(VintedApiInterface.class);
        return service.getProducts(params);
    }

    public Call<List<Product>> getProduct(HashMap<String, String> params){
        VintedApiInterface service = retrofit.create(VintedApiInterface.class);
        return service.getProduct(params);
    }

    public Call<List<User>> getUsers(HashMap<String, String> params){
        VintedApiInterface service = retrofit.create(VintedApiInterface.class);
        return service.getUsers(params);
    }

    public Call<List<User>> getUser(HashMap<String, String> params){
        VintedApiInterface service = retrofit.create(VintedApiInterface.class);
        return service.getUser(params);
    }

}
