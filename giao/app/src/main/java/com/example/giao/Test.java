package com.example.giao;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Test {
    @GET("user_api/notes_statistic_api/")
    Call<Bean> getMenuById();
}
