package com.svalero.vintedandroid.add_product.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.add_product.contract.AddProductContract;
import com.svalero.vintedandroid.add_product.presenter.AddProductPresenter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;

import static com.svalero.vintedandroid.UserMenuActivity.USER;


public class AddProductActivity extends AppCompatActivity implements AddProductContract.View {

    public TextInputEditText edtProductName;
    public TextInputEditText edtPrice;
    public TextInputEditText edtDescription;
    public AutoCompleteTextView actvCategories;
    public Button btnAdd;

    private String productName;
    private double price;
    private String description;
    private int categoryId;
    private User user;
    private String selection;

    private AddProductPresenter addProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

        initComponents();
        createDropDownMenu();

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable(USER);

        btnAdd.setOnClickListener(v -> {
            addProductPresenter = new AddProductPresenter(this);
            addProductPresenter.addProduct(this, mountProductToAdd());
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.slide_down);
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
        product.setIdUser(user.getId());
        product.setIdCategory(categoryId);

        return product;
    }

    private void createDropDownMenu(){
        String[] categories = {"Hombre", "Mujer", "Junior"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        actvCategories.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        actvCategories.setThreshold(1);
        actvCategories.setOnItemClickListener((parent, view, position, id)->{
            String itemSelected = categories[position];
            selection = itemSelected;
        });
    }

    private void initComponents() {
        edtProductName = findViewById(R.id.activity_addProduct_tv_name);
        edtPrice = findViewById(R.id.activity_addProduct_tv_price);
        edtDescription = findViewById(R.id.activity_addProduct_tv_description);
        actvCategories = findViewById(R.id.activity_addProduct_actv_category);
        btnAdd = findViewById(R.id.btnAdd);
    }

}
