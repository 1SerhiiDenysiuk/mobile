package com.example.mobiletasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SomeActivity extends AppCompatActivity {

    private Button btnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.some_activity_layout);
        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(view -> startActivity(new Intent(SomeActivity.this, DataListFragment.class)));
    }
}