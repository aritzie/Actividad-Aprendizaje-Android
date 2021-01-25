package com.svalero.vintedandroid.showProduct.view;

import android.os.Bundle;
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
    public TextView tvRate;
    public TextView tvPrice;
    public TextView tvDescription;

    private int productId;
    private ShowProductPresenter showProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        productId = bundle.getInt(PRODUCT_ID);

        Product product = new Product();
        product.setId(productId);

        showProductPresenter = new ShowProductPresenter(this);
        showProductPresenter.getProduct(product);
    }

    @Override
    public void succes(Product product) {
        tvName.setText(product.getName());
        tvRate.setText(String.valueOf(product.getRate()));
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initComponents(){
        tvName = findViewById(R.id.txtNameProduct);
        tvRate = findViewById(R.id.txtProductRate);
        tvPrice = findViewById(R.id.txtProductPrice);
        tvDescription = findViewById(R.id.txtDescription);
    }


}
