package com.example.giao.utils;

import com.example.giao.Api.Login;
import com.example.giao.Api.Register;
import com.example.giao.Api.SendVerification;
import com.example.giao.Api.User;
import com.example.giao.Test;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private Test service;
    private  retrofit2.Retrofit retrofit;
    private User userinfo;
    private SendVerification verification;
    private Register register;
    private Login login;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit() {
        retrofit = new retrofit2.Retrofit.Builder()
//                .baseUrl("https://syzzjw.cn/")
                .baseUrl("http://ij8cy3yo.shenzhuo.vip:11601/")
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

    public SendVerification getVerification(){
        verification = retrofit.create(SendVerification.class);
        return verification;
    }

    public Register getRegisterResult(){
        register = retrofit.create(Register.class);
        return register;
    }

    public Login getLoginResult(){
        login = retrofit.create(Login.class);
        return login;
    }

}
