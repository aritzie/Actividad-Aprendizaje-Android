package com.svalero.vintedandroid.list_products_by_user.view;

import android.app.Activity;
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
import com.svalero.vintedandroid.adapter.ListProductAdapter;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_products_by_user.contract.ListProductsByUserContract;
import com.svalero.vintedandroid.list_products_by_user.presenter.ListProductsByUserPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListProductsByUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListProductsByUserFragment extends Fragment implements ListProductsByUserContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private ListProductsByUserPresenter listProductsByUserPresenter;
    private Context context;
    private Activity activity;

    private View layoutError;
    private TextView textViewError;
    private Button buttonRetry;
    private ProgressBar progressBarLoading;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USER = "user";

    // TODO: Rename and change types of parameters
    private User mUser;

    public ListProductsByUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment ListProductsByUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListProductsByUserFragment newInstance(User user) {
        ListProductsByUserFragment fragment = new ListProductsByUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        activity = requireActivity();
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_products, container, false);

        recycler = view.findViewById(R.id.recyclerListProducts);
        layoutError = view.findViewById(R.id.fragment_list_products_layout_error);
        textViewError = view.findViewById(R.id.fragment_list_products_tv_error);
        buttonRetry = view.findViewById(R.id.fragment_list_products_button_retry);
        progressBarLoading = view.findViewById(R.id.fragment_list_products_progressbar_loading);

        progressBarLoading.setVisibility(View.VISIBLE);

        listProductsByUserPresenter = new ListProductsByUserPresenter(this);
        listProductsByUserPresenter.getProducts(context, mUser);

        setListener();

        return view;
    }

    @Override
    public void succes(ArrayList<Product> products) {
        showDataInRecyclerView(products);
    }

    @Override
    public void error(String message) {
        showError(message);
    }

    private void showDataInRecyclerView(ArrayList<Product> products){
        progressBarLoading.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        lManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(lManager);

        ListProductAdapter adapter = new ListProductAdapter(products, context, activity);
        recycler.setAdapter(adapter);
    }

    private void showError(String message){
        progressBarLoading.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        textViewError.setText(message);
    }

    private void setListener(){
        buttonRetry.setOnClickListener(v -> {
            progressBarLoading.setVisibility(View.VISIBLE);
            layoutError.setVisibility(View.GONE);

            listProductsByUserPresenter.getProducts(context, mUser);
        });
    }
}