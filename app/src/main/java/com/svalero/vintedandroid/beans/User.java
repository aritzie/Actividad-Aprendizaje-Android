package com.svalero.vintedandroid.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    private static final String ID = "idUser";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NUMBER_SALES = "nSales";

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int numberSales;

    public static ArrayList<User> getArrayListUserFromJSON(JSONArray listUsers) {
        ArrayList<User> list = null;

        try {
            if(listUsers != null && listUsers.length()>0){
                list = new ArrayList<>();
            }
            for (int i=0 ; i<10 ; i++) {
                JSONObject json_data = listUsers.getJSONObject(i);
                User user = new User();

                user.setId(json_data.getInt(ID));
                user.setName(json_data.getString(NAME));
                user.setLastName(json_data.getString(LAST_NAME));
                user.setEmail(json_data.getString(EMAIL));
                user.setPassword(json_data.getString(PASSWORD));
                user.setNumberSales(json_data.getInt(NUMBER_SALES));

                list.add(user);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static User getUserFromJSON(JSONArray userJson) {
        User user = null;
        if(userJson != null && userJson.length()>0){
            user = new User();
        }
        try {
            JSONObject json_data = userJson.getJSONObject(0);

            user.setId(json_data.getInt(ID));
            user.setName(json_data.getString(NAME));
            user.setLastName(json_data.getString(LAST_NAME));
            user.setEmail(json_data.getString(EMAIL));
            user.setPassword(json_data.getString(PASSWORD));
            user.setNumberSales(json_data.getInt(NUMBER_SALES));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberSales() {
        return numberSales;
    }

    public void setNumberSales(int numberSales) {
        this.numberSales = numberSales;
    }
}
