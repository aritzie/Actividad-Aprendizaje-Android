package com.svalero.vintedandroid.add_product.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;

public interface AddProductContract {
    interface View {
        void success(String message);
        void error(String message);
    }
    interface Presenter {
        void addProduct(Context context, Product product);
    }
    interface Model{
        void addProductWS(Context context, OnAddProductListener onAddProductListener, Product product);

        interface OnAddProductListener{
            void onFinished(String message);
            void onFailure(String message);
        }
    }
}
