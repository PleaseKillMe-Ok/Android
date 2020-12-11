package com.example.giao;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private Test service;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://syzzjw.cn/")
//                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Test.class);
    }

    /**
     * 获取IBeanService实例
     * @return
     */
    public Test getService(){
        return service;
    }
}
