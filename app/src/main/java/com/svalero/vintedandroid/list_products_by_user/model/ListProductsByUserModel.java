package com.svalero.vintedandroid.list_products_by_user.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products_by_user.contract.ListProductsByUserContract;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsByUserModel implements ListProductsByUserContract.Model {

    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_USER
    // &ID_USER=1

    @Override
    public void getProductsWS(Context context, OnListProductsByUserListener onListProductsByUserListener, User user) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_USER");
        params.put("ID_USER", String.valueOf(user.getId()));
        final Call<List<Product>> batch = apiClient.getProducts(params);

        batch.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response != null && response.body() != null){
                    onListProductsByUserListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                onListProductsByUserListener.onReject(t.getLocalizedMessage());
            }
        });
    }
}
