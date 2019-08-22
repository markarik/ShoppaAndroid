package com.example.homeactivity.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeactivity.R;
import com.example.homeactivity.models.LoginModel;
import com.example.homeactivity.models.UsersModel;
import com.example.homeactivity.networking.RetrofitClient;
import com.example.homeactivity.ui.activities.ProductActivity;
import com.example.homeactivity.utils.SharedPreferencesConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin, buttonSkipLogin;
    TextView redirectRegister, redirectForgotPassword;
    EditText usernameInputText, passwordInputText;
    ImageView logoImage;
    private Context mContext;
    private String buyerusername, email, token, buyerfirstname, buyerlastname;

    private SharedPreferencesConfig sharedPreferencesConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferencesConfig = new SharedPreferencesConfig(getApplicationContext());

        buttonLogin = findViewById(R.id.button_login);
        buttonSkipLogin = findViewById(R.id.button_skip_login);
        redirectForgotPassword =findViewById(R.id.redirect_forgot_password);
        redirectRegister = findViewById(R.id.redirect_register);
        usernameInputText = findViewById(R.id.username_input_text);
        passwordInputText = findViewById(R.id.password_input_text);
        logoImage = findViewById(R.id.logo_image);
        logoImage.setMaxHeight(R.dimen.logo_height);

        buttonLogin.setOnClickListener(this);
        buttonSkipLogin.setOnClickListener(this);
        redirectRegister.setOnClickListener(this);
        redirectForgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_login) {
            login();

        }
        else if (id == R.id.redirect_register)
        {
            Intent in = new Intent(this, RegisterActivity.class);
            startActivity(in);
            finish();
        }
        else if (id == R.id.redirect_forgot_password)
        {
            Intent intent=  ResetPasswordActivity.startSelfIntent(this,false);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.button_skip_login)
        {
            Intent i = new Intent(this, ProductActivity.class);
            startActivity(i);
            Toast.makeText(this,"Welcome To Shoppy",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void login(){
        String username = usernameInputText.getText().toString();
        String password = passwordInputText.getText().toString();

//        UserClient userClient = ServiceGenerator.createService(UserClient.class, username, password);
//        Call<UsersModel> call = userClient.basicLogin();

        LoginModel login = new LoginModel(username,password);
        Call<UsersModel> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .login(login);

        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                    buyerusername = response.body().getUsername();
                    buyerfirstname = response.body().getFirstName();
                    buyerlastname = response.body().getLastName();
                    email = response.body().getEmail();

                    sharedPreferencesConfig.saveAuthenticationInformation(token,buyerusername,buyerfirstname, buyerlastname,email,"Active");

                    welcome();

                }
                else{
                    Toast.makeText(LoginActivity.this,"Login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error", Toast.LENGTH_SHORT).show();
                Log.d("Error", "Another One");
            }
        });


    }

    private void welcome() {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Welcome To Shoppy",Toast.LENGTH_SHORT).show();
        finish();
    }

}
