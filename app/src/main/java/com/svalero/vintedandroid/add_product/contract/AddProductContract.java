package com.svalero.vintedandroid.add_product.contract;

import com.svalero.vintedandroid.beans.Product;

public interface AddProductContract {
    interface View {
        void success(String message);
        void error(String message);
    }
    interface Presenter {
        void addProduct(Product product);
    }
    interface Model{
        void addProductWS(OnAddProductListener onAddProductListener, Product product);

        interface OnAddProductListener{
            void onFinished(String message);
            void onFailure(String message);
        }
    }
}
