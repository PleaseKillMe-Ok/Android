package com.example.giao.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.Api.Login;
import com.example.giao.Bean.LoginRequest;
import com.example.giao.Bean.Verification;
import com.example.giao.R;
import com.example.giao.utils.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private TextView textViewRegister;
    private TextView textViewVerify;
    private Button buttonLogin;
    private LoginRequest loginRequest;
    private Login login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewVerify = findViewById(R.id.tvLoginVerify);
        buttonLogin = findViewById(R.id.buttonLogin);

        retrofit = Retrofit.getRetrofit();
        login = retrofit.getLoginResult();

        textViewRegister.setOnClickListener(new View.OnClickListener() {    //转向新用户注册页面
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(it);
            }
        });

        textViewVerify.setOnClickListener(new View.OnClickListener() {      //转向验证码登录页面
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, VerifyActivity.class);
                LoginActivity.this.startActivity(it);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {      //登录按钮点击事件
            @Override
            public void onClick(View v) {
                Login();

            }
        });
    }

    private void Login(){
        EditText editTextPhone = findViewById(R.id.etPhone);
        EditText editTextPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        System.out.println(phone);
        System.out.println(password);

        loginRequest = new LoginRequest();
        loginRequest.setPhone(phone);
        loginRequest.setWay("password");
        loginRequest.setPassword(password);

        if(phone.length() < 1) {
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 1){
            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_LONG).show();
            return;
        }


        Call<Verification> call = login.getLoginResult(loginRequest);      //发送异步请求
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {      ///** Returns true if {@link #code()} is in the range [200..300)
                    Verification result = response.body();//关键
                    if (result != null) {
                        Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                        String token = result.getToken();
                        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);   //将token保存至SharedPreferences
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("user_token",token);
                        editor.commit();

//                        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);   //从SharedPreferences获取数据
//                        String name=preferences.getString("user_token", "default_token");
                    }
                }
                if(response.code()==400){
                    Toast.makeText(LoginActivity.this,"手机号或密码错误！",Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                System.out.println("请求失败");
                Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_LONG).show();
            }
        });
    }
}
