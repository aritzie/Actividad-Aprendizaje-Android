package com.svalero.vintedandroid.list_products.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListProductAdapter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products.contract.ListProductsContract;
import com.svalero.vintedandroid.list_products.presenter.ListProductsPresenter;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity implements ListProductsContract.View {

    private ListProductsPresenter listProductsPresenter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        listProductsPresenter = new ListProductsPresenter(this);
        listProductsPresenter.getProducts();
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
