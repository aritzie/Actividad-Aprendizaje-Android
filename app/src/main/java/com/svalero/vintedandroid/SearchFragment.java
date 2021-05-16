package com.svalero.vintedandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.list_products_by_category.view.ListProductsByCategoryFragment;
import com.svalero.vintedandroid.list_products_by_name.view.ListProductsByNameFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private TextInputLayout textInputLayout;
    private TabLayout tabLayout;
    private TextInputEditText textInputEditText;
    private Button buttonSearch;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        textInputLayout = view.findViewById(R.id.fragment_search_textInput);
        tabLayout = view.findViewById(R.id.fragment_search_tabLayout);
        textInputEditText = view.findViewById(R.id.fragment_search_textInputEditText);
        buttonSearch = view.findViewById(R.id.search_fragment_button);

        initTabLayout();
        setListener();
        showListMenProducts();

        return  view;
    }

    private void initTabLayout(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            Product product = new Product();
            Fragment fragment = null;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        product.setIdCategory(2);
                        fragment = ListProductsByCategoryFragment.newInstance(product);
                        break;
                    case 2:
                        product.setIdCategory(3);
                        fragment = ListProductsByCategoryFragment.newInstance(product);
                        break;
                    default:
                        product.setIdCategory(1);
                        fragment = ListProductsByCategoryFragment.newInstance(product);
                        break;
                }

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.search_fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showListMenProducts(){
        Product product = new Product();
        product.setIdCategory(1);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        ListProductsByCategoryFragment fragment = ListProductsByCategoryFragment.newInstance(product);
        transaction.add(R.id.search_fragment_container, fragment);
        transaction.commit();
    }

    private void setListener(){
        buttonSearch.setOnClickListener((v)->{
            String productName = String.valueOf(textInputEditText.getText());
            Product product = new Product();
            product.setName(productName);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            ListProductsByNameFragment fragment = ListProductsByNameFragment.newInstance(product);
            transaction.replace(R.id.search_fragment_container, fragment);
            transaction.commit();
        });
    }

}