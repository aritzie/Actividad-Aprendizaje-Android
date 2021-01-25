package com.svalero.vintedandroid.login_user.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.UserMenuActivity;
import com.svalero.vintedandroid.beans.User;
import com.svalero.vintedandroid.list_users.view.ListUsersActivity;
import com.svalero.vintedandroid.login_user.contract.LoginUserContract;
import com.svalero.vintedandroid.login_user.presenter.LoginUserPresenter;

import static com.svalero.vintedandroid.UserMenuActivity.USER_ID;

public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View {

    public EditText edtEmail;
    public EditText edtPassword;
    public Button btnLogin;

    private LoginUserPresenter loginUserPresenter;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        initComponents();

        btnLogin.setOnClickListener(v -> tryLogin());
    }

    @Override
    public void succesLogin(User user) {
        Toast.makeText(this, "Bienvenido " + user.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), UserMenuActivity.class);
        intent.putExtra(USER_ID, user.getId());
        startActivity(intent);
    }

    @Override
    public void failureLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initComponents(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void tryLogin(){
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        loginUserPresenter = new LoginUserPresenter(this);
        loginUserPresenter.getUser(user);
    }
}
