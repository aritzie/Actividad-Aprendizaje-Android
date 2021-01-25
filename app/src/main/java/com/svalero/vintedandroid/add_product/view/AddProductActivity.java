package com.svalero.vintedandroid.add_product.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.add_product.contract.AddProductContract;
import com.svalero.vintedandroid.add_product.presenter.AddProductPresenter;
import com.svalero.vintedandroid.beans.Product;

import static com.svalero.vintedandroid.UserMenuActivity.USER_ID;


public class AddProductActivity extends AppCompatActivity implements AddProductContract.View {

    public EditText edtProductName;
    public EditText edtPrice;
    public EditText edtDescription;
    public Spinner spnCategories;
    public Button btnAdd;

    private String productName;
    private double price;
    private String description;
    private int categoryId;
    private int userId;

    private AddProductPresenter addProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initComponents();

        createSpinner();

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt(USER_ID);

        btnAdd.setOnClickListener(v -> {
            addProductPresenter = new AddProductPresenter(this);
            addProductPresenter.addProduct(mountProductToAdd());
        });

    }

    @Override
    public void success(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Product mountProductToAdd(){
        productName = edtProductName.getText().toString();
        price = Double.parseDouble(edtPrice.getText().toString());
        description = edtDescription.getText().toString();
        String selection = spnCategories.getSelectedItem().toString();
        switch (selection){
            case "Hombre":
                categoryId = 1;
                break;
            case "Mujer":
                categoryId = 2;
                break;
            case "Junior":
                categoryId = 3;
                break;
        }
        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setDescription(description);
        product.setIdUser(userId);
        product.setIdCategory(categoryId);

        return product;
    }

    private void initComponents() {
        edtProductName = findViewById(R.id.edtProductName);
        edtPrice = findViewById(R.id.edtPrice);
        edtDescription = findViewById(R.id.edtDescription);
        spnCategories = findViewById(R.id.spnCategories);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void createSpinner(){
        String[] categories = {"Hombre", "Mujer", "Junior"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        spnCategories.setAdapter(arrayAdapter);
    }
}
