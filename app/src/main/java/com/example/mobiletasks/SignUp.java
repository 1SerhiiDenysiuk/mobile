package com.example.mobiletasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private EditText createEmailField;
    private EditText createPasswordField;
    private EditText createName;
    private EditText createPhoneNumber;
    private FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "[a-zA-Z]/[0-9]{8,}";
    String phoneNumberPattern = "[+3]+[8]+[0]+[0-9]{9,9}";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        createEmailField = findViewById(R.id.create_email);
        createPasswordField = findViewById(R.id.create_password);
        createName = findViewById(R.id.create_user_name);
        createPhoneNumber = findViewById(R.id.create_phone_number);
        findViewById(R.id.sign_up_button).setOnClickListener(v -> {
            final String email = createEmailField.getText().toString();
            final String password = createPasswordField.getText().toString();
            final String name = createName.getText().toString();
            final String phone_number = createPhoneNumber.getText().toString();
            signUp(email, password);
        });
        findViewById(R.id.sign_in_button).setOnClickListener(v -> {
            final Intent sign_in = new Intent(this, MainActivity.class);
            startActivity(sign_in);
            finish();
        });
    }

    private void signUp(final String email, final String password) {
        if ((createEmailField.getText().length() == 0)) {
            createEmailField.setHint("email cant be empty");
        } else if (createEmailField.getText().toString().trim().matches(emailPattern)) {
            if (password.length() >= 8) {
                if (createPhoneNumber.getText().toString().trim().matches(phoneNumberPattern)) {
                    authSuccess(email, password);
                } else {
                    createPhoneNumber.setText("");
                    createPhoneNumber.setHint("incorrect phone number");
                    authFailed();
                }
            } else {
                createPasswordField.setHint("need at least 8 chapters");
                createPasswordField.setText("");
                authFailed();
            }
        } else {
            createEmailField.setHint("invalid email address");
            createEmailField.setText("");
            authFailed();
        }
    }

    public void authFailed() {
        Toast.makeText(SignUp.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
    }

    public void authSuccess(final String email, final String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(SignUp.this, "that email is used",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        final Intent logged_in = new Intent(this, HomePage.class);
        logged_in.putExtra("name", createName.getText().toString());
        startActivity(logged_in);
        finish();
    }

    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
