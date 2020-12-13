package com.example.giao.Api;

import com.example.giao.Bean.Information;
import com.example.giao.Bean.Verification;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SendVerification {
    @POST("auth-api/code-api")
    Call<Verification> getVerification();
}

