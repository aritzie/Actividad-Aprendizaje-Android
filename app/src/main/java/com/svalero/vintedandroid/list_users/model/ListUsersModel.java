package com.svalero.vintedandroid.list_users.model;

import android.os.AsyncTask;
import android.os.Handler;

import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.contract.ListUsersContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListUsersModel implements ListUsersContract.Model {

    private final String URL = "http://192.168.1.29:8084/Vinted/Controller";
    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=USER.FIND_ALL
    private ArrayList<User> listArrayUsers;
    private OnListUsersListener onListUsersListener;


    @Override
    public void getUsersWS(OnListUsersListener onListUsersListener) {
        this.onListUsersListener = onListUsersListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "USER.FIND_ALL");
        GeListUserFromAPI geListUserFromAPI = new GeListUserFromAPI(params);
        geListUserFromAPI.execute();
    }

    class GeListUserFromAPI extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> params;

        public GeListUserFromAPI(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            Post post = new Post();

            JSONArray listUsers = post.getServerDataPost(params, URL);
            listArrayUsers = User.getArrayListUserFromJSON(listUsers);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
                onListUsersListener.onResolve(listArrayUsers);
            }else {
                onListUsersListener.onReject("Error al traer los datos del server");
            }
        }
    }

}
