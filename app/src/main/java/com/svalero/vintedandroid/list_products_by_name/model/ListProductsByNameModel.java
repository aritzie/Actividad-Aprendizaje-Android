package com.svalero.vintedandroid.list_products_by_name.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_name.contract.ListProductsByNameContract;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsByNameModel implements ListProductsByNameContract.Model {

    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_NAME
    // &NAME=Camisa

    @Override
    public void getProductsWS(Context context, OnFilterProductsByNameListener onFilterProductsByNameListener, Product product) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_NAME");
        params.put("NAME", product.getName());
        final Call<List<Product>> batch = apiClient.getProducts(params);

        batch.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response != null && response.body() != null){
                    onFilterProductsByNameListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                onFilterProductsByNameListener.onReject(t.getLocalizedMessage());
            }
        });
    }
}
