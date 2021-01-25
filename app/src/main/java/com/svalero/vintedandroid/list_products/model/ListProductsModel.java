package com.svalero.vintedandroid.list_products.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListProductsModel implements ListProductsContract.Model {

    private static final String URL = "http://192.168.1.29:8084/Vinted/Controller";
    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_ALL
    private ArrayList<Product> listArrayProducts;
    private OnListProductsListener onListProductsListener;

    @Override
    public void getProductsWS(OnListProductsListener onListProductsListener) {
        this.onListProductsListener = onListProductsListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_ALL");
        GetListProductFromAPI getListProductFromAPI = new GetListProductFromAPI(params);
        getListProductFromAPI.execute();
    }

    class GetListProductFromAPI extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> params;

        public GetListProductFromAPI(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            Post post = new Post();

            JSONArray listProducts = post.getServerDataPost(params, URL);
            listArrayProducts = Product.getArrayListProductsFromJSON(listProducts, 10);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
                onListProductsListener.onResolve(listArrayProducts);
            } else {
                onListProductsListener.onReject("Error al traer los datos del servidor");
            }

        }
    }

}
