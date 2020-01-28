package com.example.mobiletasks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("equipment")
        //http get буде йти по шляху equipment
    Call<List<Equipment>> getEquipment();
}
