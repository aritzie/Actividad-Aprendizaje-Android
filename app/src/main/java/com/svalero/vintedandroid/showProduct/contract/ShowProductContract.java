package com.svalero.vintedandroid.showProduct.contract;

import com.svalero.vintedandroid.beans.Product;

public interface ShowProductContract {
    interface View {
        void succes(Product product);
        void error(String message);
    }
    interface Presenter{
        void getProduct(Product product);
    }
    interface Model{
        void getProductWS(OnShowProductListener onShowProductListener, Product product);

        interface OnShowProductListener {
            void onResolve(Product product);
            void onReject(String error);
        }
    }
}
