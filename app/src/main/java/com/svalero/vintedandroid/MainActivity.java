package com.svalero.vintedandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.svalero.vintedandroid.login_user.view.LoginUserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(
                () -> {
                    Intent intent = new Intent(
                            getBaseContext(), LoginUserActivity.class);
                    startActivity(intent);
                }
                , 4000
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.slide_down);
    }
}