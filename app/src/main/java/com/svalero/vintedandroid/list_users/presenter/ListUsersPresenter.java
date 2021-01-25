package com.svalero.vintedandroid.list_users.presenter;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.contract.ListUsersContract;
import com.svalero.vintedandroid.list_users.model.ListUsersModel;

import java.util.ArrayList;

public class ListUsersPresenter implements ListUsersContract.Presenter {

    private ListUsersModel listUsersModel;
    private ListUsersContract.View view;

    public ListUsersPresenter(ListUsersContract.View view) {
        this.view = view;
        this.listUsersModel = new ListUsersModel();
    }

    @Override
    public void getUsers() {

        listUsersModel.getUsersWS(new ListUsersContract.Model.OnListUsersListener() {
            @Override
            public void onResolve(ArrayList<User> users) {
                view.success(users);
            }

            @Override
            public void onReject(String error) {
                view.error(error);
            }
        });

    }
}
