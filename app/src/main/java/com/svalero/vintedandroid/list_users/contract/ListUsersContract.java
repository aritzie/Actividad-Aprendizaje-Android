package com.svalero.vintedandroid.list_users.contract;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;

import java.util.ArrayList;

public interface ListUsersContract {
    interface View {
        void success(ArrayList<User> users);
        void error (String message);
    }

    interface Presenter {
        void getUsers();
    }

    interface Model {
        void getUsersWS(OnListUsersListener onListUsersListener);

        interface OnListUsersListener {
            void onResolve(ArrayList<User> users);
            void onReject(String error);
        }
    }
}
