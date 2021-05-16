package com.svalero.vintedandroid.list_products_by_user.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;

import java.util.ArrayList;

public interface ListProductsByUserContract {
    interface View {
        void succes(ArrayList<Product> products);
        void error(String message);
    }
    interface Presenter {
        void getProducts(Context context, User user);
    }
    interface Model {
        void  getProductsWS(Context context, OnListProductsByUserListener onListProductsByUserListener, User user);

        interface OnListProductsByUserListener {
            void onResolve(ArrayList<Product> products);
            void onReject(String error);
        }
    }
}
