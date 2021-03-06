package com.svalero.vintedandroid.login_user.presenter;

import android.content.Context;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.login_user.contract.LoginUserContract;
import com.svalero.vintedandroid.login_user.model.LoginUserModel;

public class LoginUserPresenter implements LoginUserContract.Presenter {

    LoginUserModel loginUserModel;
    private LoginUserContract.View view;

    public LoginUserPresenter(LoginUserContract.View view) {
        this.view = view;
        loginUserModel = new LoginUserModel();
    }

    @Override
    public void getUser(Context context, User user) {
        loginUserModel.getUserWS(context, new LoginUserContract.Model.OnLoginUserListener() {
            @Override
            public void onFinished(User user) {
                view.succesLogin(user);
            }

            @Override
            public void onFailure(String error) {
                view.failureLogin(error);
            }
        }, user);
    }
}
