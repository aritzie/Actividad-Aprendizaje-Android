package com.svalero.vintedandroid.list_products_by_user.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListProductAdapter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products_by_user.contract.ListProductsByUserContract;
import com.svalero.vintedandroid.list_products_by_user.presenter.ListProductsByUserPresenter;

import java.util.ArrayList;

import static com.svalero.vintedandroid.UserMenuActivity.USER_ID;

public class ListProductsByUserActivity extends AppCompatActivity implements ListProductsByUserContract.View {

    private ListProductsByUserPresenter listProductsByUserPresenter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt(USER_ID);

        User user = new User();
        user.setId(userId);

        listProductsByUserPresenter = new ListProductsByUserPresenter(this);
        listProductsByUserPresenter.getProducts(user);
    }


    @Override
    public void succes(ArrayList<Product> products) {
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
