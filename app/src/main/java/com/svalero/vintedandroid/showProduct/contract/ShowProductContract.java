package com.svalero.vintedandroid.showProduct.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.Product;

public interface ShowProductContract {
    interface View {
        void succes(Product product);
        void error(String message);
    }
    interface Presenter{
        void getProduct(Context context, Product product);
    }
    interface Model{
        void getProductWS(Context context, OnShowProductListener onShowProductListener, Product product);

        interface OnShowProductListener {
            void onResolve(Product product);
            void onReject(String error);
        }
    }
}
