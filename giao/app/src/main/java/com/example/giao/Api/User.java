package com.example.giao.Api;

import com.example.giao.Bean.Information;

import retrofit2.Call;
import retrofit2.http.GET;

public interface User {
    @GET("auth-api/information-api")
    Call<Information> getInformation();

}
