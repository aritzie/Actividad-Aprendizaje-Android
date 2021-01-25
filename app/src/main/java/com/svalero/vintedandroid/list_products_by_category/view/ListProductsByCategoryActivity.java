package com.svalero.vintedandroid.list_products_by_category.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListProductAdapter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_category.contract.ListProductsByCategoryContract;
import com.svalero.vintedandroid.list_products_by_category.presenter.ListProductsByCategoryPresenter;



import java.util.ArrayList;

import static com.svalero.vintedandroid.UserMenuActivity.CATEGORY_ID;

public class ListProductsByCategoryActivity extends AppCompatActivity implements ListProductsByCategoryContract.View {

    private ListProductsByCategoryPresenter listProductsByCategoryPresenter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        Bundle bundle = getIntent().getExtras();
        categoryId = bundle.getInt(CATEGORY_ID);
        Product product = new Product();
        product.setIdCategory(categoryId);
        listProductsByCategoryPresenter = new ListProductsByCategoryPresenter(this);
        listProductsByCategoryPresenter.getProducts(product);
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
