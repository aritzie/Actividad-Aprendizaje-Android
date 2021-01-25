package com.svalero.vintedandroid.list_products_by_user.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products_by_user.contract.ListProductsByUserContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListProductsByUserModel implements ListProductsByUserContract.Model {

    private static String URL = "http://192.168.1.29:8084/Vinted/Controller";
    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_USER
    // &ID_USER=1
    private ArrayList<Product> listArrayProducts;
    private OnListProductsByUserListener onListProductsByUserListener;

    @Override
    public void getProductsWS(OnListProductsByUserListener onListProductsByUserListener, User user) {
        this.onListProductsByUserListener = onListProductsByUserListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_USER");
        params.put("ID_USER", String.valueOf(user.getId()));
        GetProductsFromAPI getProductsFromAPI = new GetProductsFromAPI(params);
        getProductsFromAPI.execute();
    }



    class GetProductsFromAPI extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String, String> params;

        public GetProductsFromAPI(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            JSONArray listProducts = post.getServerDataPost(params, URL);
            listArrayProducts = Product.getArrayListProductsFromJSON(listProducts, listProducts.length());

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
              onListProductsByUserListener.onResolve(listArrayProducts);
            } else {
                onListProductsByUserListener.onReject("Error al traer los datos del server");
            }
        }
    }
}
