package com.svalero.vintedandroid.showProduct.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.retrofit.ApiClient;
import com.svalero.vintedandroid.showProduct.contract.ShowProductContract;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowProductModel implements ShowProductContract.Model {

    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_ID
    // &ID_PRODUCT=1"

    @Override
    public void getProductWS(Context context, OnShowProductListener onShowProductListener, Product product) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String,String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_ID");
        params.put("ID_PRODUCT", String.valueOf(product.getId()));
        final Call<List<Product>> batch = apiClient.getProduct(params);

        batch.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response != null &&  response.body() != null){
                    onShowProductListener.onResolve(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                onShowProductListener.onReject(t.getLocalizedMessage());
            }
        });
    }
}
