package com.shawnfrye.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginButtonClick(View view) {
        // Logic to navigate to a different activity
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    public void onSignupButtonClick(View view) {
        // Logic to navigate to a different activity
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }
}