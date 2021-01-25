package com.svalero.vintedandroid.list_products_by_name.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListProductAdapter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_name.contract.ListProductsByNameContract;
import com.svalero.vintedandroid.list_products_by_name.presenter.ListProductsByNamePresenter;

import java.util.ArrayList;

import static com.svalero.vintedandroid.UserMenuActivity.PRODUCT_NAME;

public class ListProductsByNameActivity extends AppCompatActivity implements ListProductsByNameContract.View {

    private ListProductsByNamePresenter listProductsByNamePresenter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        Bundle bundle = getIntent().getExtras();
        productName = bundle.getString(PRODUCT_NAME);
        Product product = new Product();
        product.setName(productName);
        listProductsByNamePresenter = new ListProductsByNamePresenter(this);
        listProductsByNamePresenter.getProducts(product);
    }

    @Override
    public void success(ArrayList<Product> products) {
        recycler = findViewById(R.id.recyclerList);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        ListProductAdapter adapter = new ListProductAdapter(products, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {

    }
}
