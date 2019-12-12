package com.example.mobiletasks;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private ApiService apiService;
    private FirebaseAuth auth;

    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
        apiService = createApiService();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public ApiService getApiService() {
        return apiService;
    }

    private ApiService createApiService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-mobile-tasks-6f21f.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
