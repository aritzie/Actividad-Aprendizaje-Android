package com.svalero.vintedandroid.add_product.model;

import android.content.Context;

import com.svalero.vintedandroid.add_product.contract.AddProductContract;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductModel implements AddProductContract.Model {

    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.ADD
    // &NAME=Prueba
    // &PRICE=5.5
    // &DESCRIPTION=Prueba
    // &ID_USER=1
    // &ID_CATEGORY=1

    @Override
    public void addProductWS(Context context, OnAddProductListener onAddProductListener, Product product) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.ADD");
        params.put("NAME", product.getName());
        params.put("PRICE", String.valueOf(product.getPrice()));
        params.put("DESCRIPTION", product.getDescription());
        params.put("ID_USER", String.valueOf(product.getIdUser()));
        params.put("ID_CATEGORY", String.valueOf(product.getIdCategory()));
        final Call<Product> batch = apiClient.addProduct(params);

        batch.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                onAddProductListener.onFinished("Producto añadido");
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                onAddProductListener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
