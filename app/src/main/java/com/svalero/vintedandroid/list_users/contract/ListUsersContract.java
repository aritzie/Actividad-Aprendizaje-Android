package com.svalero.vintedandroid.list_users.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;

import java.util.ArrayList;

public interface ListUsersContract {
    interface View {
        void success(ArrayList<User> users);
        void error (String message);
    }

    interface Presenter {
        void getUsers(Context context);
    }

    interface Model {
        void getUsersWS(Context context, OnListUsersListener onListUsersListener);

        interface OnListUsersListener {
            void onResolve(ArrayList<User> users);
            void onReject(String error);
        }
    }
}
