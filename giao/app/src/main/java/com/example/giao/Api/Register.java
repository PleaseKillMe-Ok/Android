package com.example.giao.Api;

import com.example.giao.Bean.RegisterRequest;
import com.example.giao.Bean.RequestResult;
import com.example.giao.Bean.Verification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Register {
    @POST("auth-api/register-api")
    Call<Verification> getRegisterResult(@Body RegisterRequest registerRequest);
}
