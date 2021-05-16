package com.svalero.vintedandroid.list_products_by_name.presenter;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_name.contract.ListProductsByNameContract;
import com.svalero.vintedandroid.list_products_by_name.model.ListProductsByNameModel;

import java.util.ArrayList;

public class ListProductsByNamePresenter implements ListProductsByNameContract.Presenter {

    private ListProductsByNameModel listProductsByNameModel;
    private ListProductsByNameContract.View view;

    public ListProductsByNamePresenter(ListProductsByNameContract.View view) {
        this.view = view;
        this.listProductsByNameModel = new ListProductsByNameModel();
    }

    @Override
    public void getProducts(Context context, Product product) {
        listProductsByNameModel.getProductsWS(context, new ListProductsByNameContract.Model.OnFilterProductsByNameListener() {
            @Override
            public void onResolve(ArrayList<Product> products) {
                view.success(products);
            }

            @Override
            public void onReject(String error) {
                view.error("Problemas al traer los datos");
            }
        }, product);
    }
}
