package com.svalero.vintedandroid.list_products_by_category.presenter;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_category.contract.ListProductsByCategoryContract;
import com.svalero.vintedandroid.list_products_by_category.model.ListProductsByCategoryModel;

import java.util.ArrayList;

public class ListProductsByCategoryPresenter implements ListProductsByCategoryContract.Presenter {

    private ListProductsByCategoryModel listProductsModel;
    private ListProductsByCategoryContract.View view;

    public ListProductsByCategoryPresenter(ListProductsByCategoryContract.View view) {
        this.view = view;
        this.listProductsModel = new ListProductsByCategoryModel();
    }


    @Override
    public void getProducts(Product product) {
        listProductsModel.getProductsWS(new ListProductsByCategoryContract.Model.OnProductsByCategoryListener() {
            @Override
            public void onResolve(ArrayList<Product> products) {
                view.success(products);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        }, product);
    }
}
