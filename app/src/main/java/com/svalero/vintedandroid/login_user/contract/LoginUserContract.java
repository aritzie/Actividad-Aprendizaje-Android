package com.svalero.vintedandroid.login_user.contract;

import com.svalero.vintedandroid.beans.User;

public interface LoginUserContract {
    interface View {
        void succesLogin(User user);
        void failureLogin(String message);
    }
    interface Presenter {
        void getUser(User user);
    }
    interface Model {
        void getUserWS(OnLoginUserListener onLoginUserListener, User user);

        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String error);
        }
    }
}
