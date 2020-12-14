package com.example.giao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textViewRegister = findViewById(R.id.textViewRegister);
        TextView textViewVerify = findViewById(R.id.tvLoginVerify);
//        textViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent();
//                it.setClass(LoginActivity.this, RegisterActivity.class);
//                LoginActivity.this.startActivity(it);
//            }
//        });
//        textViewVerify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent();
//                it.setClass(LoginActivity.this, VerifyActivity.class);
//                LoginActivity.this.startActivity(it);
//            }
//        });
//
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(it);
            }
        });
        textViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, VerifyActivity.class);
                LoginActivity.this.startActivity(it);
            }
        });


    }
}

//public class LoginActivity extends AppCompatActivity {
//    Button btnLogin;
//    EditText editTextName,editTextPwd;
//    TextView textViewRegister;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        btnLogin= this.findViewById(R.id.buttonLogin);
//        editTextName= this.findViewById(R.id.editTextName);
//        editTextPwd= this.findViewById(R.id.editTextPassword);
//        textViewRegister= this.findViewById(R.id.textViewRegister);
//        //实现登录功能
//        btnLogin.setOnClickListener(view -> {
//            login();
//
//        });
//        //实现注册功能
//        textViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent();
//                it.setClass(LoginActivity.this, RegisterActivity.class);
//                LoginActivity.this.startActivity(it);
//            }
//        });
//
//    }
//
//    private void register()//跳转到注册页面
//    {
//        Intent intent=new Intent();
//        intent.setClass(LoginActivity.this,RegisterActivity.class);
//        startActivity(intent);
//    }
//
//    private void login()//登录校准
//    {
//        String name=editTextName.getText().toString();
//        String pwd=editTextPwd.getText().toString();
//        if(name.equals("admin") && pwd.equals("admin"))
//        {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(this,"登录失败！",Toast.LENGTH_LONG).show();
//        }
//    }
//}