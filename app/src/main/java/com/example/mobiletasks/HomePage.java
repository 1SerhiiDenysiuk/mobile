package com.example.mobiletasks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private TextView username;
    private FirebaseAuth auth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        username = findViewById(R.id.logged_in_screen_message);
        auth = FirebaseAuth.getInstance();
        Button nextBtn = findViewById(R.id.home_activity_nextBtn);
        findViewById(R.id.sign_out_button).setOnClickListener(v -> {
            final Intent signOut = new Intent(this, MainActivity.class);
            startActivity(signOut);
        });
        updateUI();
        nextBtn.setOnClickListener(view -> startActivity(new Intent(HomePage.this, DataList.class)));
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            username.setText(getString(R.string.welcome) + user.getDisplayName());
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
