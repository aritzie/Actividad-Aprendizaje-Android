package com.svalero.vintedandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.svalero.vintedandroid.add_product.view.AddProductActivity;
import com.svalero.vintedandroid.beans.User;

public class UserMenuActivity extends AppCompatActivity {

    public static final String USER = "user";
    public static final String CATEGORY_ID = "idCategory";
    public static final String PRODUCT_NAME = "productName";

    public BottomNavigationView bottomNavigationView;

    private User user;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        overridePendingTransition(R.anim.zoom_in, 0);

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable(USER);

        initComponents();
        showInitFragment();
        initBottomNavigation();
    }

    private void initBottomNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_nav_2:
                    Fragment searchFragment = SearchFragment.newInstance();
                    doTransactionFragment(searchFragment);
                    break;
                case R.id.menu_nav_3:
                    Intent intent = new Intent(this, AddProductActivity.class);
                    intent.putExtra(USER, user);
                    startActivity(intent);
                    break;
                case R.id.menu_nav_4:
                    Fragment profileFragment = ProfileFragment.newInstance(user);
                    doTransactionFragment(profileFragment);
                    break;
                default:
                    Fragment initFragment = InitFragment.newInstance();
                    doTransactionFragment(initFragment);
                    break;
            }

            return true;
        });
    }

    private void showInitFragment(){
        InitFragment initFragment = InitFragment.newInstance();
        doTransactionFragment(initFragment);
    }

    private void doTransactionFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initComponents(){
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
    }
}
