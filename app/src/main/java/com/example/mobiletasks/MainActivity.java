package com.example.mobiletasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.login_screen_email);
        passwordField = findViewById(R.id.login_screen_password);
        findViewById(R.id.login_screen_sign_in_button).setOnClickListener(v -> {
            final String email = emailField.getText().toString();
            final String password = passwordField.getText().toString();
            signIn(email, password);
        });
        findViewById(R.id.login_screen_sign_up_button).setOnClickListener(v -> {
            final Intent sign_up = new Intent(this, SignUp.class);
            startActivity(sign_up);
            finish();
        });
    }

    private void signIn(final String email, final String password) {
        if (emailField.getText().toString().trim().matches(emailPattern)) {
            if (passwordField.getText().length() == 0) {
                passwordField.setHint("password cant be empty");
            } else {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                onSignInSuccess();

                            } else {
                                onSignInFailure();
                                passwordField.setText("");
                            }

                        });
            }
        } else {
            emailField.setText("");
            emailField.setHint("invalid email address");
        }
    }

    private void onSignInSuccess() {
        final Intent sign_in = new Intent(this, HomePage.class);
        String name = getIntent().getStringExtra("name");
        sign_in.putExtra("name", name);
        startActivity(sign_in);
        finish();
    }

    private void onSignInFailure() {
        Toast.makeText(MainActivity.this, "incorrect email or password",
                Toast.LENGTH_LONG).show();
    }

    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
