package com.svalero.vintedandroid.list_users.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.contract.ListUsersContract;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersModel implements ListUsersContract.Model {

    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=USER.FIND_ALL
    @Override
    public void getUsersWS(Context context, OnListUsersListener onListUsersListener) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "USER.FIND_ALL");
        final Call<List<User>> batch = apiClient.getUsers(params);

        batch.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response != null && response.body() != null){
                    onListUsersListener.onResolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                onListUsersListener.onReject(t.getLocalizedMessage());
            }
        });
    }
}
