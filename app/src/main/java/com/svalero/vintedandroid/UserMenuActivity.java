package com.svalero.vintedandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.svalero.vintedandroid.add_product.view.AddProductActivity;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products.view.ListProductsActivity;
import com.svalero.vintedandroid.list_products_by_category.view.ListProductsByCategoryActivity;
import com.svalero.vintedandroid.list_products_by_name.view.ListProductsByNameActivity;
import com.svalero.vintedandroid.list_products_by_user.view.ListProductsByUserActivity;
import com.svalero.vintedandroid.list_users.view.ListUsersActivity;

public class UserMenuActivity extends AppCompatActivity {

    public static final String USER_ID = "idUser";
    public static final String CATEGORY_ID = "idCategory";
    public static final String PRODUCT_NAME = "productName";

    public Button btnUserProducts;
    public Chip chipMen;
    public Chip chipWomen;
    public Chip chipJunior;
    public Button btnBestProducts;
    public Button btnBestUsers;
    public Button btnToAdd;
    public EditText edtName;
    public Button btnSearch;

    private int userId;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt(USER_ID);

        initComponents();

        btnSearch.setOnClickListener(v -> searchProductByName());
        btnUserProducts.setOnClickListener(v -> listUserProducts());
        chipMen.setOnClickListener(v -> {
            categoryId = 1;
            listProductsByCategory(categoryId);
        });
        chipWomen.setOnClickListener(v -> {
            categoryId = 2;
            listProductsByCategory(categoryId);
        });
        chipJunior.setOnClickListener(v -> {
            categoryId = 3;
            listProductsByCategory(categoryId);
        });
        btnBestProducts.setOnClickListener(v -> listBestProducts());
        btnBestUsers.setOnClickListener(v -> listBestUsers());
        btnToAdd.setOnClickListener(v -> goToAddProduct());
    }

    private void searchProductByName() {
        String productName = edtName.getText().toString();
        Intent intent = new Intent(getBaseContext(), ListProductsByNameActivity.class);
        intent.putExtra(PRODUCT_NAME, productName);
        startActivity(intent);
    }

    private void listUserProducts() {
        Intent intent = new Intent(getBaseContext(), ListProductsByUserActivity.class);
        intent.putExtra(USER_ID, userId);
        startActivity(intent);
    }

    private void listProductsByCategory(int parameter){
        Intent intent = new Intent(getBaseContext(), ListProductsByCategoryActivity.class);
        intent.putExtra(CATEGORY_ID, parameter);
        startActivity(intent);
    }

    private void listBestProducts() {
        Intent intent = new Intent(getBaseContext(), ListProductsActivity.class);
        startActivity(intent);
    }

    private void listBestUsers() {
        Intent intent = new Intent(getBaseContext(), ListUsersActivity.class);
        startActivity(intent);
    }

    private void goToAddProduct() {
        Intent intent = new Intent(getBaseContext(), AddProductActivity.class);
        intent.putExtra(USER_ID, userId);
        startActivity(intent);
    }

    private void initComponents(){
        btnUserProducts = findViewById(R.id.btnUserProducts);
        chipMen = findViewById(R.id.chipMen);
        chipWomen = findViewById(R.id.chipWomen);
        chipJunior = findViewById(R.id.chipJunior);
        btnBestProducts = findViewById(R.id.btnBestProducts);
        btnBestUsers = findViewById(R.id.btnBestUsers);
        btnToAdd = findViewById(R.id.btnToAdd);
        edtName = findViewById(R.id.edtSearchName);
        btnSearch = findViewById(R.id.btnSearch);
    }
}
