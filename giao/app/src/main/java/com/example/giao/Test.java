package com.example.giao;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Test {
    @GET("user_api/notes_statistic_api/")
    Call<Beansss> getMenuById();
}
