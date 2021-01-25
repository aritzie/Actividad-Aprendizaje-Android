package com.svalero.vintedandroid.list_users.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListUserAdapter;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.contract.ListUsersContract;
import com.svalero.vintedandroid.list_users.presenter.ListUsersPresenter;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity implements ListUsersContract.View {

    private ListUsersPresenter listUsersPresenter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        listUsersPresenter = new ListUsersPresenter(this);
        listUsersPresenter.getUsers();
    }

    @Override
    public void success(ArrayList<User> users) {
        recycler = findViewById(R.id.recyclerList);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        ListUserAdapter adapter = new ListUserAdapter(users, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
