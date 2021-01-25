package com.svalero.vintedandroid.list_products_by_name.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_name.contract.ListProductsByNameContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListProductsByNameModel implements ListProductsByNameContract.Model {

    private static String URL = "http://192.168.1.29:8084/Vinted/Controller";
    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_NAME
    // &NAME=Camisa
    private ArrayList<Product> listArrayProducts;
    private OnFilterProductsByNameListener onFilterProductsByNameListener;


    @Override
    public void getProductsWS(OnFilterProductsByNameListener onFilterProductsByNameListener, Product product) {
        this.onFilterProductsByNameListener = onFilterProductsByNameListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_NAME");
        params.put("NAME", product.getName());
        GetListFilterProductByNameFromAPI getListFilterProductByNameFromAPI = new GetListFilterProductByNameFromAPI(params);
        getListFilterProductByNameFromAPI.execute();
    }

    class GetListFilterProductByNameFromAPI extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> params;

        public GetListFilterProductByNameFromAPI(HashMap<String, String> params) {
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
            if(resp) {
                onFilterProductsByNameListener.onResolve(listArrayProducts);
            } else {
                onFilterProductsByNameListener.onReject("Problemas al traer los datos del servidor");
            }
        }
    }

}
