package com.svalero.vintedandroid.showProduct.presenter;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.showProduct.contract.ShowProductContract;
import com.svalero.vintedandroid.showProduct.model.ShowProductModel;

public class ShowProductPresenter implements ShowProductContract.Presenter {

    private ShowProductModel showProductModel;
    private ShowProductContract.View view;

    public ShowProductPresenter(ShowProductContract.View view){
        this.view = view;
        showProductModel = new ShowProductModel();
    }

    @Override
    public void getProduct(Product product) {
        showProductModel.getProductWS(new ShowProductContract.Model.OnShowProductListener() {
            @Override
            public void onResolve(Product product) {
                view.succes(product);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        }, product);
    }
}
