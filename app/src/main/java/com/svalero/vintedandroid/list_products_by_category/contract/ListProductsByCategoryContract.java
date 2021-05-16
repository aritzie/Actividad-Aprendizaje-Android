package com.svalero.vintedandroid.list_products_by_category.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;

import java.util.ArrayList;

public interface ListProductsByCategoryContract {
    interface View {
        void success(ArrayList<Product> products);
        void error (String message);
    }

    interface Presenter {
        void getProducts(Context context, Product product);
    }

    interface Model {
        void getProductsWS(Context context, OnProductsByCategoryListener onProductsByCategoryListener, Product product);

        interface OnProductsByCategoryListener {
            void onResolve(ArrayList<Product> products);
            void onReject(String error);
        }
    }
}
