package com.svalero.vintedandroid.list_products_by_user.presenter;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products_by_user.contract.ListProductsByUserContract;
import com.svalero.vintedandroid.list_products_by_user.model.ListProductsByUserModel;


import java.util.ArrayList;


public class ListProductsByUserPresenter implements ListProductsByUserContract.Presenter {

    private ListProductsByUserModel listProductsByUserModel;
    private ListProductsByUserContract.View view;

    public ListProductsByUserPresenter(ListProductsByUserContract.View view) {
        this.view = view;
        listProductsByUserModel = new ListProductsByUserModel();
    }

    @Override
    public void getProducts(Context context, User user) {
        listProductsByUserModel.getProductsWS(context, new ListProductsByUserContract.Model.OnListProductsByUserListener() {
            @Override
            public void onResolve(ArrayList<Product> products) {
                view.succes(products);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        }, user);
    }
}
