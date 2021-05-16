package com.svalero.vintedandroid.login_user.model;

import android.content.Context;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.login_user.contract.LoginUserContract;
import com.svalero.vintedandroid.retrofit.ApiClient;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements LoginUserContract.Model {

    //http://localhost:8084/Vinted/Controller
    // ?ACTION=USER.LOGIN
    // &EMAIL=mrhyde@hyde.com
    // &PASSWORD=1234
    @Override
    public void getUserWS(Context context, OnLoginUserListener onLoginUserListener, User user) {
        ApiClient apiClient = new ApiClient(context);
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "USER.LOGIN");
        params.put("PASSWORD", user.getPassword());
        params.put("EMAIL", user.getEmail());
        final Call<List<User>> batch = apiClient.getUser(params);

        batch.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response != null && response.body() != null){
                    onLoginUserListener.onFinished(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
