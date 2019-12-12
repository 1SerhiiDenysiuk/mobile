package com.example.mobiletasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static final int passwordMinLength = 8;
    private EditText emailField;
    private EditText passwordField;
    private FirebaseAuth auth;

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
            final Intent signUp = new Intent(this, SignUp.class);
            startActivity(signUp);
            finish();
        });
    }

    private void signIn(final String email, final String password) {
        if (validateEmail() && validatePassword()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            onSignInSuccess();
                        } else {
                            onSignInFailure();
                        }
                    });
        }
    }

    private void onSignInSuccess() {
        final Intent signIn = new Intent(this, HomePage.class);
        String name = getIntent().getStringExtra("name");
        signIn.putExtra("name", name);
        startActivity(signIn);
        finish();
    }

    private void onSignInFailure() {
        Toast.makeText(MainActivity.this, R.string.wrong_email_or_password,
                Toast.LENGTH_LONG).show();
        passwordField.setText("");
        passwordField.setHint("@string/password");
    }

    private boolean validateEmail() {
        if (emailField.getText().toString().isEmpty()) {
            emailField.setText("");
            emailField.setHint(R.string.empty_email_error);
            return false;
        } else if (!emailField.getText().toString().trim().matches(emailPattern)) {
            emailField.setText("");
            emailField.setHint(R.string.wrong_email);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        if (passwordField.getText().toString().isEmpty()) {
            passwordField.setHint(R.string.empty_password_error);
            return false;
        } else if (passwordField.length() < passwordMinLength) {
            passwordField.setText("");
            passwordField.setHint(R.string.short_password_error);
            return false;
        } else {
            return true;
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

