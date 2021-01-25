package com.svalero.vintedandroid.list_products.presenter;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;
import com.svalero.vintedandroid.list_products.model.ListProductsModel;

import java.util.ArrayList;

public class ListProductsPresenter implements ListProductsContract.Presenter {

    private ListProductsModel listProductsModel;
    private ListProductsContract.View view;

    public ListProductsPresenter(ListProductsContract.View view) {
        this.view = view;
        this.listProductsModel = new ListProductsModel();
    }

    @Override
    public void getProducts() {
        listProductsModel.getProductsWS(new ListProductsContract.Model.OnListProductsListener() {
            @Override
            public void onResolve(ArrayList<Product> products) {
                view.success(products);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        });
    }
}
