package com.svalero.vintedandroid.list_products_by_name.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;


import java.util.ArrayList;

public interface ListProductsByNameContract {
    interface View {
        void success(ArrayList<Product> products);
        void error (String message);
    }

    interface Presenter {
        void getProducts(Context context, Product product);
    }

    interface Model {
        void getProductsWS(Context context, OnFilterProductsByNameListener onFilterProductsByNameListener, Product product);

        interface OnFilterProductsByNameListener {
            void onResolve(ArrayList<Product> products);
            void onReject(String error);
        }
    }
}
