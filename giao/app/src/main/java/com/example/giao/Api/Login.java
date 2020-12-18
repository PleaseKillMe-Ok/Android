package com.example.giao.Api;

import com.example.giao.Bean.LoginRequest;
import com.example.giao.Bean.Verification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Login {
    @POST("auth-api/login-api")
    Call<Verification> getLoginResult(@Body LoginRequest loginRequest);
}
