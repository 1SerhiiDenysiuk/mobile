package com.example.mobiletasks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("equipment")
        //http get буде йти по шляху equipment
    Call<List<Equipment>> getEquipment();
    @POST("equipment")
    Call<Equipment> createEquipment(@Body Equipment equipment);
}
