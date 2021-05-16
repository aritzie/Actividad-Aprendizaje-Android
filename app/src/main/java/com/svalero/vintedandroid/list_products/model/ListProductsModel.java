package com.svalero.vintedandroid.list_products.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsModel implements ListProductsContract.Model {

    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_ALL

    @Override
    public void getProductsWS(Context context, OnListProductsListener onListProductsListener) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_ALL");

        final Call<List<Product>> batch = apiClient.getProducts(params);

        batch.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response != null && response.body() != null){
                    onListProductsListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                onListProductsListener.onReject(t.getLocalizedMessage());
            }
        });
    }
}
