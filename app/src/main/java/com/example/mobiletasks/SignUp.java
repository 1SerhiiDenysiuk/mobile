package com.example.mobiletasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {

    private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static final String phoneNumberPattern = "[+3]+[8]+[0]+[0-9]{9,9}";
    static final int passwordMinLength = 8;
    static final int nameMinLength = 4;
    private EditText editedEmail;
    private EditText editedPassword;
    private EditText editedName;
    private EditText editedPhoneNumber;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        editedEmail = findViewById(R.id.create_email);
        editedPassword = findViewById(R.id.create_password);
        editedName = findViewById(R.id.create_user_name);
        editedPhoneNumber = findViewById(R.id.create_phone_number);
        findViewById(R.id.sign_up_button).setOnClickListener(v -> {
            final String email = editedEmail.getText().toString();
            final String password = editedPassword.getText().toString();
            signUpLogic(email, password);
        });
        findViewById(R.id.sign_in_button).setOnClickListener(v -> {
            final Intent signIn = new Intent(this, MainActivity.class);
            startActivity(signIn);
            finish();
        });

    }

    private void signUpLogic(final String email, final String password) {
        if (validateEmail() && validatePhoneNumber() && validateName() && validatePassword()) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    authSuccess();
                } else {
                    Toast.makeText(SignUp.this, R.string.used_email_error,
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            authFailed();
        }
    }

    private void authFailed() {
        Toast.makeText(SignUp.this, R.string.auth_failed,
                Toast.LENGTH_SHORT).show();
    }

    private void authSuccess() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(editedName.getText().toString()).build();
            user.updateProfile(profileChangeRequest).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(this, HomePage.class));
                    finish();
                } else {
                    Toast.makeText(SignUp.this, R.string.used_email_error,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateEmail() {
        if (!editedEmail.getText().toString().trim().matches(emailPattern)) {
            editedEmail.setText("");
            editedEmail.setHint(R.string.wrong_email);
            return false;
        } else if (editedEmail.getText().toString().isEmpty()) {
            editedEmail.setHint(R.string.empty_email_error);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        if (editedPassword.length() < passwordMinLength) {
            editedPassword.setText("");
            editedPassword.setHint(R.string.short_password_error);
            return false;
        } else if (editedPassword.getText().toString().isEmpty()) {
            editedPassword.setText("");
            editedPassword.setHint(R.string.empty_password_error);
            return false;
        } else {
            return true;
        }
    }

    private boolean validateName() {
        if (editedName.length() < nameMinLength) {
            editedName.setText("");
            editedName.setHint(R.string.short_name_error);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        if (!editedPhoneNumber.getText().toString().trim().matches(phoneNumberPattern)) {
            editedPhoneNumber.setText("");
            editedPhoneNumber.setHint(R.string.wrong_phone_number);
            return false;
        } else {
            return true;
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
