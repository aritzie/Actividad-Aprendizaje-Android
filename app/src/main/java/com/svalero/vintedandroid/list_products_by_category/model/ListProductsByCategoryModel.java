package com.svalero.vintedandroid.list_products_by_category.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_category.contract.ListProductsByCategoryContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListProductsByCategoryModel implements ListProductsByCategoryContract.Model {

    private static final String URL = "http://192.168.1.29:8084/Vinted/Controller";
    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_CATEGORY
    // &CATEGORY=1
    private ArrayList<Product> listArrayProducts;
    private OnProductsByCategoryListener onProductsByCategoryListener;

    @Override
    public void getProductsWS(OnProductsByCategoryListener onProductsByCategoryListener, Product product) {
        this.onProductsByCategoryListener = onProductsByCategoryListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_CATEGORY");
        params.put("CATEGORY", String.valueOf(product.getIdCategory()));
        GetListFilterProductByCategoryFromAPI getListProductFromAPI = new GetListFilterProductByCategoryFromAPI(params);
        getListProductFromAPI.execute();
    }

    class GetListFilterProductByCategoryFromAPI extends AsyncTask<String, Integer, Boolean>{

        private HashMap<String, String> params;

        public GetListFilterProductByCategoryFromAPI(HashMap<String, String> params) {
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
                onProductsByCategoryListener.onResolve(listArrayProducts);
            } else {
                onProductsByCategoryListener.onReject("Error al traer los datos del servidor");
            }

        }
    }

}
