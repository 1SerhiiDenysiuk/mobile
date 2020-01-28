package com.example.mobiletasks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private TextInputLayout newUsernameLayout;
    private TextInputEditText newUsernameField;
    private TextInputLayout newEmailLayout;
    private TextInputEditText newEmailField;
    private Button logoutBtn;
    private Button usernameSubmitBtn;
    private Button emailSubmitBtn;
    private Button pictureBtn;
    private TextView username;
    private TextView email;
    private ImageView profilePicture;
    private FirebaseUser user;
    private StorageReference Folder;
    private StorageReference ImageName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(Objects.requireNonNull(getView()));
        getUserInfo();

        usernameSubmitBtn.setOnClickListener(view1 -> onPressUsernameUpdateBtn());

        emailSubmitBtn.setOnClickListener(view12 -> onPressEmailUpdateBtn());

        pictureBtn.setOnClickListener(view13 -> uploadProfilePicture());

        logoutBtn.setOnClickListener(view14 -> onPressLogoutBtn());
    }

    private void onPressLogoutBtn() {
        auth.signOut();
        Intent i = new Intent(getActivity(), SignIn.class);
        startActivity(i);
        Objects.requireNonNull(getActivity()).overridePendingTransition(0, 0);
    }

    private void onPressEmailUpdateBtn() {
        String newEmail = Objects.requireNonNull(newEmailField.getText()).toString().trim();
        if (isEmailValid(newEmail)){
            updateEmail(user, newEmail);
        }
    }

    private void onPressUsernameUpdateBtn() {
        String newUsername = Objects.requireNonNull(newUsernameField.getText()).toString().trim();
        if (isUsernameValid(newUsername)){
            username.setText(newUsername);
            updateUsername(user, newUsername);
        }
    }

    private void getUserInfo() {
        user = auth.getCurrentUser();
        if (user != null) {
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());
            ImageName = Folder.child(user.getUid() + ".jpg");
            placeImage();
        } else {
            Toast.makeText(getActivity(),getString(R.string.error),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Uri ImageData = Objects.requireNonNull(data).getData();
                ImageName.putFile(Objects.requireNonNull(ImageData)).addOnSuccessListener(taskSnapshot -> Toast.makeText(getActivity(),getString(R.string.image_uploaded),Toast.LENGTH_SHORT).show());

                placeImage();
            }
        }
    }

    private void initViews(View root){
        auth = FirebaseAuth.getInstance();
        newUsernameLayout = root.findViewById(R.id.fragment_profile_layout_new_username);
        newUsernameField = root.findViewById(R.id.fragment_profile_new_username);
        newEmailLayout = root.findViewById(R.id.fragment_profile_layout_new_email);
        newEmailField = root.findViewById(R.id.fragment_profile_new_email);
        username = root.findViewById(R.id.fragment_profile_name);
        email = root.findViewById(R.id.fragment_profile_email);
        logoutBtn = root.findViewById(R.id.logout_btn);
        usernameSubmitBtn = root.findViewById(R.id.name_submit_btn);
        emailSubmitBtn = root.findViewById(R.id.email_submit_btn);
        pictureBtn = root.findViewById(R.id.upload_pic);
        profilePicture = root.findViewById(R.id.profile_picture_image_view);
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
    }

    private void uploadProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void placeImage(){
        ImageName.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri.toString()).into(profilePicture));
    }

    private void updateUsername(FirebaseUser user, String newName){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(getActivity(),getString(R.string.username_updated), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateEmail(FirebaseUser user, final String newEmail){
        user.updateEmail(newEmail).addOnCompleteListener(task -> {
            Toast.makeText(getActivity(),getString(R.string.email_updated),Toast.LENGTH_SHORT).show();
            email.setText(newEmail);
        });
    }

    private boolean isUsernameValid(String username) {
        if (username.isEmpty()) {
            newUsernameLayout.setError(getString(R.string.name));
            newUsernameLayout.requestFocus();
            return false;
        } else {
            newUsernameLayout.setError(null);
            return true;
        }
    }

    private boolean isEmailValid(String email) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            newEmailLayout.setError(getString(R.string.email));
            newEmailLayout.requestFocus();
            return false;
        } else {
            newEmailLayout.setError(null);
            return true;
        }
    }
}