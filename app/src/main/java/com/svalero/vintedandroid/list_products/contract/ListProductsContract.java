package com.svalero.vintedandroid.list_products.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;

import java.util.ArrayList;

public interface ListProductsContract {
    interface View {
        void success(ArrayList<Product> products);
        void error (String message);
    }

    interface Presenter {
        void getProducts(Context context);
    }

    interface Model {
        void getProductsWS(Context context, OnListProductsListener onListProductsListener);

        interface OnListProductsListener {
            void onResolve(ArrayList<Product> products);
            void onReject(String error);
        }
    }
}
