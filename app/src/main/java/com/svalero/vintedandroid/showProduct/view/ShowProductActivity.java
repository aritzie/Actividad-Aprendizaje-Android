package com.svalero.vintedandroid.showProduct.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.showProduct.contract.ShowProductContract;
import com.svalero.vintedandroid.showProduct.presenter.ShowProductPresenter;

public class ShowProductActivity extends AppCompatActivity implements ShowProductContract.View {

    public static final String PRODUCT_ID = "productId";

    public TextView tvName;
    public TextView tvPrice;
    public TextView tvDescription;

    private View layoutSuccess;
    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private ProgressBar progressBarLoading;

    private Product product;
    private int productId;
    private ShowProductPresenter showProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        productId = bundle.getInt(PRODUCT_ID);

        product = new Product();
        product.setId(productId);

        progressBarLoading.setVisibility(View.VISIBLE);

        showProductPresenter = new ShowProductPresenter(this);
        showProductPresenter.getProduct(this, product);

        setListener();
    }

    @Override
    public void succes(Product product) {
        showProductDetail(product);
    }

    @Override
    public void error(String message) {
        showError(message);
    }

    private void showProductDetail(Product product){
        layoutSuccess.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.GONE);

        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice() + " €");
        tvDescription.setText(product.getDescription());
    }

    private void initComponents(){
        layoutSuccess = findViewById(R.id.activity_show_product_layout_success);
        tvName = findViewById(R.id.txtNameProduct);
        tvPrice = findViewById(R.id.txtProductPrice);
        tvDescription = findViewById(R.id.txtDescription);
        layoutError = findViewById(R.id.activity_show_product_layout_error);
        textViewError = findViewById(R.id.activity_show_product_tv_error);
        buttonRetry = findViewById(R.id.activity_show_product_button_retry);
        progressBarLoading = findViewById(R.id.activity_show_products_progressbar_loading);
    }

    private void showError(String message){
        layoutSuccess.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        progressBarLoading.setVisibility(View.GONE);
        textViewError.setText(message);
    }

    private void setListener(){
        buttonRetry.setOnClickListener(v -> {
            layoutError.setVisibility(View.GONE);
            progressBarLoading.setVisibility(View.VISIBLE);

            showProductPresenter.getProduct(this, product);
        });
    }

}
