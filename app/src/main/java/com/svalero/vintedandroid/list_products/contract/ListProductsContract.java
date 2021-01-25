package com.svalero.vintedandroid.list_products.contract;

import com.svalero.vintedandroid.beans.Product;

import java.util.ArrayList;

public interface ListProductsContract {
    interface View {
        void success(ArrayList<Product> products);
        void error (String message);
    }

    interface Presenter {
        void getProducts();
    }

    interface Model {
        void getProductsWS(OnListProductsListener onListProductsListener);

        interface OnListProductsListener {
            void onResolve(ArrayList<Product> products);
            void onReject(String error);
        }
    }
}
