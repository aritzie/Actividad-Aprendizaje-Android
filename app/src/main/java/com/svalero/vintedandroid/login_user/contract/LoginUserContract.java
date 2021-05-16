package com.svalero.vintedandroid.login_user.contract;

import android.content.Context;

import com.svalero.vintedandroid.beans.User;

public interface LoginUserContract {
    interface View {
        void succesLogin(User user);
        void failureLogin(String message);
    }
    interface Presenter {
        void getUser(Context context, User user);
    }
    interface Model {
        void getUserWS(Context context, OnLoginUserListener onLoginUserListener, User user);

        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String error);
        }
    }
}
