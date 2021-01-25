package com.svalero.vintedandroid.showProduct.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.showProduct.contract.ShowProductContract;
import com.svalero.vintedandroid.utils.Post;

import org.json.JSONArray;

import java.util.HashMap;

public class ShowProductModel implements ShowProductContract.Model {

    private static String URL = "http://192.168.1.29:8084/Vinted/Controller";
    private Product product;
    private OnShowProductListener onShowProductListener;
    //http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.FIND_BY_ID
    // &ID_PRODUCT=1"
    @Override
    public void getProductWS(OnShowProductListener onShowProductListener, Product product) {
        this.onShowProductListener = onShowProductListener;
        HashMap<String,String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.FIND_BY_ID");
        params.put("ID_PRODUCT", String.valueOf(product.getId()));
        GetProductFromAPI getProductFromAPI = new GetProductFromAPI(params);
        getProductFromAPI.execute();
    }

    class GetProductFromAPI extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String, String> params;

        public GetProductFromAPI(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            JSONArray productJson = post.getServerDataPost(params, URL);
            product = Product.getProductFromJSON(productJson);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp) {
                onShowProductListener.onResolve(product);
            }else{
                onShowProductListener.onReject("Error al traer los datos del servidor");
            }
        }
    }
}
