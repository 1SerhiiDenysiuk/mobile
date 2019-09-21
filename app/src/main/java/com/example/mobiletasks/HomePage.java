package com.example.mobiletasks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView personName = findViewById(R.id.logged_in_screen_message);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        personName.setText("Welcome, " + getIntent().getStringExtra("name"));
        findViewById(R.id.sign_out_button).setOnClickListener(v -> {
            final Intent sign_out = new Intent(this, MainActivity.class);
            startActivity(sign_out);
        });
    }

    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
