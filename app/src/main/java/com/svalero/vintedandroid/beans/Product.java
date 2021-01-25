package com.svalero.vintedandroid.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {

    private static final String ID = "idProduct";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String RATE = "rate";
    private static final String ID_USER = "idUser";
    private static final String ID_CATEGORY = "idCategory";

    private int id;
    private String name;
    private double price;
    private String description;
    private double rate;
    private int idUser;
    private int idCategory;

    public static ArrayList<Product> getArrayListProductsFromJSON(JSONArray listProducts, int iteractions) {
        ArrayList<Product> list = null;

        try {
            if(listProducts != null && listProducts.length()>0){
                list = new ArrayList<>();
            }
            for (int i=0 ; i<iteractions ; i++) {
                JSONObject json_data = listProducts.getJSONObject(i);
                Product product = new Product();

                product.setId(json_data.getInt(ID));
                product.setName(json_data.getString(NAME));
                product.setPrice(json_data.getDouble(PRICE));
                product.setDescription(json_data.getString(DESCRIPTION));
                product.setRate(json_data.getDouble(RATE));
                product.setIdUser(json_data.getInt(ID_USER));
                product.setIdCategory(json_data.getInt(ID_CATEGORY));

                list.add(product);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Product getProductFromJSON(JSONArray productJson) {
        Product product = null;
        try {
            if (productJson != null && productJson.length()>0) {
                product = new Product();
            }
            JSONObject json_data = productJson.getJSONObject(0);

            product.setId(json_data.getInt(ID));
            product.setName(json_data.getString(NAME));
            product.setPrice(json_data.getDouble(PRICE));
            product.setDescription(json_data.getString(DESCRIPTION));
            product.setRate(json_data.getDouble(RATE));
            product.setIdUser(json_data.getInt(ID_USER));
            product.setIdCategory(json_data.getInt(ID_CATEGORY));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
