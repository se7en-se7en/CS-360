package com.shawnfrye.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Signup extends AppCompatActivity {
    public static boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onSignupButtonClick(View view) {
        // navigate to a different activity
        Intent intent = new Intent(Signup.this, MainActivity.class);
        startActivity(intent);
        status = true;

    }
    public static boolean getStatus() {
        return status;
    }
}