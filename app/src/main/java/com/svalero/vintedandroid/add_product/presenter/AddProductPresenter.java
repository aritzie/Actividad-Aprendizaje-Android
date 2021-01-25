package com.svalero.vintedandroid.add_product.presenter;

import com.svalero.vintedandroid.add_product.contract.AddProductContract;
import com.svalero.vintedandroid.add_product.model.AddProductModel;
import com.svalero.vintedandroid.add_product.view.AddProductActivity;
import com.svalero.vintedandroid.beans.Product;

public class AddProductPresenter implements AddProductContract.Presenter {

    private AddProductModel addProductModel;
    private AddProductContract.View view;

    public AddProductPresenter(AddProductContract.View view) {
        this.view = view;
        addProductModel = new AddProductModel();
    }

    @Override
    public void addProduct(Product product) {
        addProductModel.addProductWS(new AddProductContract.Model.OnAddProductListener() {
            @Override
            public void onFinished(String message) {
                view.success(message);
            }

            @Override
            public void onFailure(String message) {
                view.error(message);
            }
        }, product);
    }
}
