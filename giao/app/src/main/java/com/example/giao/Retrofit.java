package com.example.giao;

import com.example.giao.Api.User;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private Test service;
    private  retrofit2.Retrofit retrofit;
    private User userinfo;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit() {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://syzzjw.cn/")
//                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Test getService(){

        service = retrofit.create(Test.class);
        return service;
    }
    public User getUserInfo(){
        userinfo = retrofit.create(User.class);
        return userinfo;
    }
}
