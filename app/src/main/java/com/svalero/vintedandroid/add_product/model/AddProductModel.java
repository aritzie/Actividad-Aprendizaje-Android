package com.svalero.vintedandroid.add_product.model;

import android.os.AsyncTask;

import com.svalero.vintedandroid.add_product.contract.AddProductContract;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.utils.Post;

import java.util.HashMap;

public class AddProductModel implements AddProductContract.Model {

    private static String URL = "http://192.168.1.29:8084/Vinted/Controller";
    // http://192.168.1.29:8084/Vinted/Controller
    // ?ACTION=PRODUCT.ADD
    // &NAME=Prueba
    // &PRICE=5.5
    // &DESCRIPTION=Prueba
    // &ID_USER=1
    // &ID_CATEGORY=1
    private OnAddProductListener onAddProductListener;

    @Override
    public void addProductWS(OnAddProductListener onAddProductListener, Product product) {
        this.onAddProductListener = onAddProductListener;
        HashMap<String, String> params = new HashMap<>();
        params.put("ACTION", "PRODUCT.ADD");
        params.put("NAME", product.getName());
        params.put("PRICE", String.valueOf(product.getPrice()));
        params.put("DESCRIPTION", product.getDescription());
        params.put("ID_USER", String.valueOf(product.getIdUser()));
        params.put("ID_CATEGORY", String.valueOf(product.getIdCategory()));
        AddProductToDataBase addProductToDataBase = new AddProductToDataBase(params);
        addProductToDataBase.execute();
    }

    class AddProductToDataBase extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String, String> params;

        public AddProductToDataBase(HashMap<String, String> params) {
            this.params = params;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            post.getServerDataPost(params, URL);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if(resp){
                onAddProductListener.onFinished("Producto añadido correctamente");
            } else {
                onAddProductListener.onFailure("Error al añadir el producto");
            }
        }
    }
}
