package com.svalero.vintedandroid.login_user.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.login_user.contract.LoginUserContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;

import java.util.HashMap;

public class LoginUserModel implements LoginUserContract.Model {

    private static String URL = "http://192.168.1.29:8084/Vinted/Controller";
    private User user;
    private OnLoginUserListener onLoginUserListener;
    //http://localhost:8084/Vinted/Controller
    // ?ACTION=USER.LOGIN
    // &EMAIL=mrhyde@hyde.com
    // &PASSWORD=1234
    @Override
    public void getUserWS(OnLoginUserListener onLoginUserListener, User user) {
        this.onLoginUserListener = onLoginUserListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "USER.LOGIN");
        params.put("PASSWORD", user.getPassword());
        params.put("EMAIL", user.getEmail());
        GetUserFromAPI getUserFromAPI = new GetUserFromAPI(params);
        getUserFromAPI.execute();
    }

    class GetUserFromAPI extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String, String> params;

        public GetUserFromAPI(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            JSONArray userJson = post.getServerDataPost(params, URL);
            user = User.getUserFromJSON(userJson);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                onLoginUserListener.onFinished(user);
            }else {
                onLoginUserListener.onFailure("Error al realizar el login");
            }
        }
    }
}
