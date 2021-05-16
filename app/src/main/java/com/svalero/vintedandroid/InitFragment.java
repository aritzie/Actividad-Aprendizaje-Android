package com.svalero.vintedandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.svalero.vintedandroid.list_products.view.ListProductsFragment;
import com.svalero.vintedandroid.list_users.view.ListUsersFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitFragment extends Fragment {

    private TabLayout tabLayout;

    public InitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InitFragment newInstance() {
        InitFragment fragment = new InitFragment();
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
        View view = inflater.inflate(R.layout.fragment_init, container, false);

        tabLayout = view.findViewById(R.id.fragment_init_tabLayout);

        initTabLayout();
        showListProductsFragment();

        return view;
    }

    private void initTabLayout(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        fragment = ListUsersFragment.newInstance();
                        break;
                    default:
                        fragment= ListProductsFragment.newInstance();
                        break;
                }

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.init_fragment_container, fragment);
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

    private void showListProductsFragment(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        ListProductsFragment listProductsFragment = ListProductsFragment.newInstance();
        transaction.add(R.id.init_fragment_container, listProductsFragment);
        transaction.commit();
    }
}