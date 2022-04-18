package com.example.mungoapp.initial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.munggoapp.R;
import com.example.mungoapp.dashboard.HomeScreenActivity;
import com.example.mungoapp.network.ApiClient;
import com.example.mungoapp.network.ApiInterface;
import com.example.mungoapp.signup.TutorialsActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogActivity extends AppCompatActivity {

    TextInputEditText userEmail, userPassword;
    TextInputLayout emailLayout, passwordLayout;
    Button signIn;
    String email, pass;
    ApiInterface apiInterface;
    TextView fingerprint, register;
    Executor executor;
    BiometricPrompt biometricPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.username_email);
        userPassword = findViewById(R.id.user_password);
        emailLayout = findViewById(R.id.emaillayout);
        passwordLayout = findViewById(R.id.passwordlayout);
        signIn = findViewById(R.id.signinbutton);
        register = findViewById(R.id.register);
        apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        fingerprint = findViewById(R.id.fingerprint);
        executor = ContextCompat.getMainExecutor(this);

        signIn.setOnClickListener((View v) -> {
            {
                try {
                    email = userEmail.getText().toString();
                    pass = userPassword.getText().toString();
                    System.out.println("Username: " + email);
                    System.out.println("Password: " + pass);
                    attemptLogin(email, pass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fingerprint.setOnClickListener(view -> {

        });

        register.setOnClickListener(view -> {
            transfer_menu(LogActivity.this, TutorialsActivity.class);
        });
    }

    public void attemptLogin (String user_email, String user_pass){
        if (TextUtils.isEmpty(user_email)){
            emailLayout.setError("Required*");
        }
        else if (TextUtils.isEmpty(user_pass)){
            passwordLayout.setError("Enter Password");
        }
        else {
            emailLayout.setError(null);
            passwordLayout.setError(null);
            loginTask();
        }
    }

    private void loginTask() {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", pass)
                .build();
        apiInterface.Login(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        assert response.body() != null;
                        String res = response.body().string();
                        System.out.println("JAVA MASTER: " + res);
                        transfer_menu(LogActivity.this, HomeScreenActivity.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, @NonNull Throwable t) {


            }
        });
    }
    public void transfer_menu(Context context, Class transferclass){
        Intent launchNextActivity;
        launchNextActivity = new Intent(context,transferclass);
        startActivity(launchNextActivity);
    }


}