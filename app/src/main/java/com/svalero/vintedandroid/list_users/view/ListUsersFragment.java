package com.svalero.vintedandroid.list_users.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.adapter.ListUserAdapter;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.contract.ListUsersContract;
import com.svalero.vintedandroid.list_users.presenter.ListUsersPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListUsersFragment extends Fragment implements ListUsersContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private ListUsersPresenter listUsersPresenter;
    private Context context;

    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private ProgressBar progressBarLoading;

    public ListUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListUsersFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static ListUsersFragment newInstance() {
        ListUsersFragment fragment = new ListUsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_users_fragments, container, false);

        recycler = view.findViewById(R.id.recyclerListUsers);
        layoutError = view.findViewById(R.id.fragment_list_users_layout_error);
        textViewError = view.findViewById(R.id.fragment_list_users_tv_error);
        buttonRetry = view.findViewById(R.id.fragment_list_users_button_retry);
        progressBarLoading = view.findViewById(R.id.fragment_list_users_progressbar_loading);

        progressBarLoading.setVisibility(View.VISIBLE);

        listUsersPresenter = new ListUsersPresenter(this);
        listUsersPresenter.getUsers(context);

        setListener();

        return view;
    }

    @Override
    public void success(ArrayList<User> users) {
        showDataInRecyclerView(users);
    }

    @Override
    public void error(String message) {
        showError(message);
    }

    private void showDataInRecyclerView(ArrayList<User> users){
        recycler.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.GONE);

        lManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(lManager);

        ListUserAdapter adapter = new ListUserAdapter(users, context);
        recycler.setAdapter(adapter);
    }

    private void showError(String message){
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        progressBarLoading.setVisibility(View.GONE);
        textViewError.setText(message);
    }

    private void setListener(){
        buttonRetry.setOnClickListener(v -> {
            layoutError.setVisibility(View.GONE);
            progressBarLoading.setVisibility(View.VISIBLE);

            listUsersPresenter.getUsers(context);
        });
    }
}